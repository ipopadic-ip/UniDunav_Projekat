package com.unidunav.dokument.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.unidunav.dokument.dto.DokumentDTO;
import com.unidunav.dokument.dto.VerzijaDokumentaDTO;
import com.unidunav.dokument.model.Dokument;
import com.unidunav.dokument.model.VerzijaDokumenta;
import com.unidunav.dokument.repository.DokumentRepository;
import com.unidunav.dokument.repository.VerzijaDokumentaRepository;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;

@Service
public class DokumentService {

    @Autowired
    private DokumentRepository dokumentRepo;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerzijaDokumentaRepository verzijaRepo;

    public List<Dokument> sviAktivniDokumenti() {
        return dokumentRepo.findByDeletedFalse();
    }

    public List<Dokument> sviDokumentiZaAdmina() {
        return dokumentRepo.findAll();
    }

    public Dokument dodajDokument(Dokument dokument) {
    	dokument.setDeleted(false); 
        return dokumentRepo.save(dokument);
    }

    public Optional<Dokument> pronadjiAktivniPoId(Long id) {
        return dokumentRepo.findByIdAndDeletedFalse(id);
    }

    public void obrisiDokument(Long id) {
        Dokument dokument = dokumentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokument nije pronađen"));
        dokument.setDeleted(true);
        dokumentRepo.save(dokument);
    }

    public void aktivirajDokument(Long id) {
        Dokument dokument = dokumentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokument nije pronađen"));
        dokument.setDeleted(false);
        dokumentRepo.save(dokument);
    }

    
    public List<VerzijaDokumentaDTO> vratiSveVerzijeDTO(Long dokumentId) {
        return verzijaRepo.findByDokumentIdOrderByBrojVerzijeDesc(dokumentId)
                .stream().map(this::mapirajVerzijuBezTeksta)
                .collect(Collectors.toList());
    }

    public VerzijaDokumentaDTO vratiVerzijuDTO(Long verzijaId) {
        VerzijaDokumenta v = verzijaRepo.findById(verzijaId)
                .orElseThrow(() -> new RuntimeException("Verzija ne postoji"));
        return mapirajVerzijuSaTekstom(v);
    }

    private VerzijaDokumentaDTO mapirajVerzijuBezTeksta(VerzijaDokumenta v) {
        VerzijaDokumentaDTO dto = new VerzijaDokumentaDTO();
        dto.setId(v.getId());
        dto.setBrojVerzije(v.getBrojVerzije());
        dto.setDatumKreiranja(v.getDatumKreiranja());
        dto.setAutor(v.getAutor().getEmail());
        dto.setPutanjaDoFajla(v.getPutanjaDoFajla());
        dto.setSadrzaj(null);
        return dto;
    }

    private VerzijaDokumentaDTO mapirajVerzijuSaTekstom(VerzijaDokumenta v) {
        VerzijaDokumentaDTO dto = new VerzijaDokumentaDTO();
        dto.setId(v.getId());
        dto.setBrojVerzije(v.getBrojVerzije());
        dto.setDatumKreiranja(v.getDatumKreiranja());
        dto.setAutor(v.getAutor().getEmail());
        dto.setPutanjaDoFajla(v.getPutanjaDoFajla());
        dto.setSadrzaj(v.getSadrzaj());
        return dto;
    }

    public VerzijaDokumenta dodajVerziju(Long dokumentId, String tekstSadrzaja, String emailAutora) {
        Dokument dokument = dokumentRepo.findById(dokumentId)
                .orElseThrow(() -> new RuntimeException("Dokument ne postoji"));

        User autor = userRepository.findByEmail(emailAutora)
                .orElseThrow(() -> new RuntimeException("Autor ne postoji"));

        int novaVerzija = verzijaRepo.findByDokumentIdOrderByBrojVerzijeDesc(dokumentId)
                .stream().findFirst().map(VerzijaDokumenta::getBrojVerzije).orElse(0) + 1;

        VerzijaDokumenta verzija = new VerzijaDokumenta();
        verzija.setDokument(dokument);
        verzija.setAutor(autor);
        verzija.setBrojVerzije(novaVerzija);
        verzija.setDatumKreiranja(LocalDateTime.now());
        verzija.setSadrzaj(tekstSadrzaja);
        verzija.setPutanjaDoFajla(null);

        return verzijaRepo.save(verzija);
    }

    public VerzijaDokumenta uploadVerzijuDokumenta(Long dokumentId, MultipartFile file, String emailAutora) throws IOException {
        Dokument dokument = dokumentRepo.findById(dokumentId)
                .orElseThrow(() -> new RuntimeException("Dokument ne postoji"));

        User autor = userRepository.findByEmail(emailAutora)
                .orElseThrow(() -> new RuntimeException("Autor ne postoji"));

        int novaVerzija = verzijaRepo.findByDokumentIdOrderByBrojVerzijeDesc(dokumentId)
                .stream().findFirst().map(VerzijaDokumenta::getBrojVerzije).orElse(0) + 1;

        String nazivFajla = "V" + novaVerzija + "_" + dokument.getNaziv().replaceAll("\\s+", "_");
        String originalExt = Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf("."));
        String finalIme = nazivFajla + originalExt;

        Path uploadPath = Paths.get("uploads/dokumenti").resolve(finalIme);
        Files.createDirectories(uploadPath.getParent());
        Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

        VerzijaDokumenta verzija = new VerzijaDokumenta();
        verzija.setDokument(dokument);
        verzija.setAutor(autor);
        verzija.setBrojVerzije(novaVerzija);
        verzija.setDatumKreiranja(LocalDateTime.now());
        verzija.setPutanjaDoFajla(uploadPath.toString());
        verzija.setSadrzaj(null);

        return verzijaRepo.save(verzija);
    }
    
    public Dokument izmeniDokument(Long id, DokumentDTO dto) {
        Dokument dokument = dokumentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokument ne postoji"));

        dokument.setNaziv(dto.getNaziv());
        dokument.setOpis(dto.getOpis());

        return dokumentRepo.save(dokument);
    }

    
    public VerzijaDokumenta dodajTekstualnuVerziju(Long dokumentId, String sadrzaj) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User nije pronađen"));

        Dokument dokument = dokumentRepo.findById(dokumentId)
                .orElseThrow(() -> new RuntimeException("Dokument ne postoji"));

        int novaVerzija = verzijaRepo.findByDokumentIdOrderByBrojVerzijeDesc(dokumentId)
                .stream().findFirst().map(VerzijaDokumenta::getBrojVerzije).orElse(0) + 1;

        VerzijaDokumenta verzija = new VerzijaDokumenta();
        verzija.setDokument(dokument);
        verzija.setAutor(user);
        verzija.setBrojVerzije(novaVerzija);
        verzija.setDatumKreiranja(LocalDateTime.now());
        verzija.setSadrzaj(sadrzaj);
        verzija.setPutanjaDoFajla(null);

        return verzijaRepo.save(verzija);
    }

    public DokumentDTO toDto(Dokument dokument) {
        DokumentDTO dto = new DokumentDTO();
        dto.setId(dokument.getId());
        dto.setNaziv(dokument.getNaziv());
        dto.setOpis(dokument.getOpis());
        dto.setDeleted(dokument.isDeleted());
        return dto;
    }



    public List<VerzijaDokumenta> prikaziVerzije(Long dokumentId) {
        return verzijaRepo.findByDokumentIdOrderByBrojVerzijeDesc(dokumentId);
    }
    public VerzijaDokumenta prikaziVerziju(Long verzijaId) {
        return verzijaRepo.findById(verzijaId).orElseThrow(() -> new RuntimeException("Verzija ne postoji"));
    }
}

