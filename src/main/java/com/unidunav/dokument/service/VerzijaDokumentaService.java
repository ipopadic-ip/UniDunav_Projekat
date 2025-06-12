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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.unidunav.dokument.dto.DokumentDTO;
import com.unidunav.dokument.dto.TekstualnaVerzijaZaKreiranjeDTO;
import com.unidunav.dokument.dto.VerzijaDokumentaDTO;
import com.unidunav.dokument.model.Dokument;
import com.unidunav.dokument.model.VerzijaDokumenta;
import com.unidunav.dokument.repository.DokumentRepository;
import com.unidunav.dokument.repository.VerzijaDokumentaRepository;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;

@Service
public class VerzijaDokumentaService {

    @Autowired
    private VerzijaDokumentaRepository verzijaRepo;

    @Autowired
    private DokumentRepository dokumentRepo;

    @Autowired
    private UserRepository userRepository;

    public List<VerzijaDokumentaDTO> getSveAktivneVerzije(Long dokumentId) {
        return verzijaRepo.findByDokumentIdAndDeletedFalse(dokumentId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<VerzijaDokumentaDTO> getSveVerzijeAdmin(Long dokumentId) {
        return verzijaRepo.findByDokumentId(dokumentId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    public List<VerzijaDokumentaDTO> getSveVerzijeAdminSve() {
        return verzijaRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public VerzijaDokumentaDTO uploadVerzijuDokumenta(Long dokumentId, MultipartFile file, String emailAutora) throws IOException {
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

        VerzijaDokumenta saved = verzijaRepo.save(verzija);
        return mapToDTO(saved);
    }

    public void obrisiVerziju(Long id) {
        VerzijaDokumenta v = verzijaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Verzija ne postoji"));
        v.setDeleted(true);
        verzijaRepo.save(v);
    }

    public void reaktivirajVerziju(Long id) {
        VerzijaDokumenta v = verzijaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Verzija ne postoji"));
        v.setDeleted(false);
        verzijaRepo.save(v);
    }
    
    public VerzijaDokumenta kreirajTekstualnuVerziju(Long dokumentId, TekstualnaVerzijaZaKreiranjeDTO dto) {
        Dokument dokument = dokumentRepo.findById(dokumentId)
                .orElseThrow(() -> new RuntimeException("Dokument ne postoji"));

        User autor = userRepository.findByEmail(dto.getAutor())
                .orElseThrow(() -> new RuntimeException("Korisnik ne postoji"));

        int novaVerzija = verzijaRepo.findByDokumentIdOrderByBrojVerzijeDesc(dokumentId)
                .stream().findFirst().map(VerzijaDokumenta::getBrojVerzije).orElse(0) + 1;

        VerzijaDokumenta verzija = new VerzijaDokumenta();
        verzija.setDokument(dokument);
        verzija.setAutor(autor);
        verzija.setDatumKreiranja(LocalDateTime.now());
        verzija.setBrojVerzije(novaVerzija);
        verzija.setSadrzaj(dto.getSadrzaj());
        verzija.setPutanjaDoFajla(null);

        return verzijaRepo.save(verzija);
    }
    
    public VerzijaDokumenta izmeniVerziju(Long id, VerzijaDokumentaDTO dto) {
        VerzijaDokumenta verzija = verzijaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Verzija ne postoji"));

        if (dto.getSadrzaj() != null) verzija.setSadrzaj(dto.getSadrzaj());
        if (dto.getPutanjaDoFajla() != null) verzija.setPutanjaDoFajla(dto.getPutanjaDoFajla());

        return verzijaRepo.save(verzija);
    }

    public VerzijaDokumentaDTO mapToDTO(VerzijaDokumenta v) {
        VerzijaDokumentaDTO dto = new VerzijaDokumentaDTO();
        dto.setId(v.getId());
        dto.setBrojVerzije(v.getBrojVerzije());
        dto.setDatumKreiranja(v.getDatumKreiranja());
        dto.setPutanjaDoFajla(v.getPutanjaDoFajla());
        dto.setSadrzaj(v.getSadrzaj());
        dto.setDeleted(v.isDeleted());
        dto.setAutor(v.getAutor() != null ? v.getAutor().getEmail() : "Nepoznat");

        // dodaj DokumentDTO
        if (v.getDokument() != null) {
            DokumentDTO dokumentDTO = new DokumentDTO(
                v.getDokument().getId(),
                v.getDokument().getNaziv(),
                v.getDokument().getOpis(),
                v.getDokument().isDeleted()
            );
            dto.setDokument(dokumentDTO);
        }

        return dto;
    }
    public Optional<VerzijaDokumenta> findLatestByDokumentId(Long dokumentId) {
        return verzijaRepo.findByDokumentIdOrderByBrojVerzijeDesc(dokumentId)
                          .stream().findFirst(); 
    }
    public VerzijaDokumentaDTO mapToDto2(VerzijaDokumenta v) {
        VerzijaDokumentaDTO dto = new VerzijaDokumentaDTO();
        dto.setId(v.getId());
        dto.setBrojVerzije(v.getBrojVerzije());
        dto.setDatumKreiranja(v.getDatumKreiranja());
        dto.setAutor(v.getAutor().getEmail());
        dto.setPutanjaDoFajla(v.getPutanjaDoFajla());
        dto.setSadrzaj(v.getSadrzaj()); // VAŽNO
        return dto;
    }
    public List<VerzijaDokumentaDTO> getSvePoslednjeVerzijeJavno() {
        List<Object[]> poslednjeVerzije = verzijaRepo.findPoslednjeVerzijePoDokumentima();

        return poslednjeVerzije.stream()
                .map(result -> {
                    VerzijaDokumenta v = (VerzijaDokumenta) result[0];
                    return mapToDTO(v);
                })
                .collect(Collectors.toList());
    }




//    public VerzijaDokumentaDTO mapToDTO(VerzijaDokumenta v) {
//        VerzijaDokumentaDTO dto = new VerzijaDokumentaDTO();
//        dto.setId(v.getId());
//        dto.setBrojVerzije(v.getBrojVerzije());
//        dto.setDatumKreiranja(v.getDatumKreiranja());
//        dto.setPutanjaDoFajla(v.getPutanjaDoFajla());
//        dto.setSadrzaj(v.getSadrzaj());
//        dto.setDeleted(v.isDeleted());
//        dto.setAutor(v.getAutor() != null ? v.getAutor().getEmail() : "Nepoznat");
//        return dto;
//    }
}

