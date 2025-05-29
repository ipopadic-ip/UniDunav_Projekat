package com.unidunav.predmet.service.prijavaIspita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.dto.PrijavaIspitaDTO;
import com.unidunav.predmet.model.PohadjanjePredmeta;
import com.unidunav.predmet.model.Predmet;
import com.unidunav.predmet.model.PrijavaIspita;
import com.unidunav.predmet.repository.PohadjanjePredmetaRepository;
import com.unidunav.predmet.repository.PredmetRepository;
import com.unidunav.predmet.repository.PrijavaIspitaRepository;

import com.unidunav.student.repository.StudentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrijavaIspitaServiceImpl implements PrijavaIspitaService {

    @Autowired
    private PrijavaIspitaRepository prijavaRepo;

    @Autowired
    private PohadjanjePredmetaRepository pohadjanjeRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private PredmetRepository predmetRepo;

    @Override
    public PrijavaIspitaDTO create(PrijavaIspitaDTO dto) {
        PrijavaIspita entity = new PrijavaIspita();
        entity.setDatumPrijave(dto.getDatumPrijave());
        entity.setDatumIspita(dto.getDatumIspita());
        entity.setStatus(dto.isStatus());

        PohadjanjePredmeta pohadjanje = pohadjanjeRepo.findById(dto.getPohadjanjeId())
                .orElseThrow(() -> new RuntimeException("Pohadjanje predmeta nije pronadjeno"));

        entity.setPohadjanje(pohadjanje);
        prijavaRepo.save(entity);

        return toDto(entity);
    }

    @Override
    public List<PrijavaIspitaDTO> findAll() {
        return prijavaRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public PrijavaIspitaDTO findById(Long id) {
        PrijavaIspita entity = prijavaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Prijava ne postoji"));
        return toDto(entity);
    }

    @Override
    public PrijavaIspitaDTO update(Long id, PrijavaIspitaDTO dto) {
        PrijavaIspita entity = prijavaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Prijava ne postoji"));

        entity.setDatumPrijave(dto.getDatumPrijave());
        entity.setDatumIspita(dto.getDatumIspita());
        entity.setStatus(dto.isStatus());

        return toDto(prijavaRepo.save(entity));
    }

    @Override
    public void delete(Long id) {
        prijavaRepo.deleteById(id);
    }

    private boolean prijavaAktivna() {
        LocalDate danas = LocalDate.now();
        LocalDate pocetak = LocalDate.of(danas.getYear(), 6, 1);
        LocalDate kraj = LocalDate.of(danas.getYear(), 6, 15);
        return !danas.isBefore(pocetak) && !danas.isAfter(kraj);
    }

    public List<PredmetDTO> getPredmetiZaPrijavu(Long studentId) {
        if (!prijavaAktivna()) return List.of();

        List<PohadjanjePredmeta> pohadjanja = pohadjanjeRepo.findByStudentId(studentId);

        return pohadjanja.stream()
            .filter(PohadjanjePredmeta::isAktivan) // uzmi samo aktivna pohadjanja
            .map(p -> {
                Predmet predmet = p.getPredmet();
                PredmetDTO dto = new PredmetDTO();
                dto.setId(predmet.getId());
                dto.setNaziv(predmet.getNaziv());
                dto.setEcts(predmet.getEcts()); // ako je polje u entitetu 'espb'
                dto.setInformacijeOPredmetu(predmet.getInformacijeOPredmetu()); // ako postoji getInformacije()
                dto.setGodinaStudijaId(predmet.getGodinaStudija().getId()); // ako je entitet povezan
                return dto;
            })
            .collect(Collectors.toList());
    }

    public void prijavi(PrijavaIspitaDTO dto) {
        if (!prijavaAktivna())
            throw new RuntimeException("Prijava nije aktivna (dozvoljena od 1. do 15. juna).");

        PohadjanjePredmeta pohadjanje = pohadjanjeRepo.findById(dto.getPohadjanjeId())
                .orElseThrow(() -> new RuntimeException("Pohadjanje predmeta nije pronadjeno."));

        boolean vecPrijavljen = pohadjanje.getPrijaveIspita().stream()
                .anyMatch(p -> p.getDatumIspita().equals(dto.getDatumIspita()));

        if (vecPrijavljen)
            throw new RuntimeException("Ispit je već prijavljen za taj datum.");

        PrijavaIspita prijava = new PrijavaIspita();
        prijava.setDatumPrijave(LocalDateTime.now());
        prijava.setDatumIspita(dto.getDatumIspita());
        prijava.setStatus(true);
        prijava.setPohadjanje(pohadjanje);

        prijavaRepo.save(prijava);
    }

    private PrijavaIspitaDTO toDto(PrijavaIspita entity) {
        PrijavaIspitaDTO dto = new PrijavaIspitaDTO();
        dto.setId(entity.getId());
        dto.setDatumPrijave(entity.getDatumPrijave());
        dto.setDatumIspita(entity.getDatumIspita());
        dto.setStatus(entity.isStatus());
        dto.setPohadjanjeId(entity.getPohadjanje().getId());
        return dto;
    }
}
