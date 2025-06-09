package com.unidunav.sluzbenik.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidunav.sluzbenik.dto.TrebovanjeKnjigeDTO;
import com.unidunav.sluzbenik.model.TrebovanjeKnjige;
import com.unidunav.sluzbenik.repository.TrebovanjeKnjigeRepository;

@Service
public class TrebovanjeKnjigeService {

    @Autowired
    private TrebovanjeKnjigeRepository repository;

    public List<TrebovanjeKnjige> findAll() {
        return repository.findAll();
    }

    public TrebovanjeKnjige save(TrebovanjeKnjigeDTO dto) {
        TrebovanjeKnjige t = new TrebovanjeKnjige();
        t.setNaziv(dto.getNaziv());
        t.setAutor(dto.getAutor());
        t.setZanr(dto.getZanr());
        t.setGodinaIzdavanja(dto.getGodinaIzdavanja());
        t.setIzdavac(dto.getIzdavac());
        t.setBrojPrimeraka(dto.getBrojPrimeraka());
        t.setDatumTrebovanja(LocalDate.now());
        t.setStatus("Na čekanju");
        return repository.save(t);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public TrebovanjeKnjige update(Long id, TrebovanjeKnjigeDTO dto) {
        TrebovanjeKnjige t = repository.findById(id).orElseThrow();
        t.setNaziv(dto.getNaziv());
        t.setAutor(dto.getAutor());
        t.setZanr(dto.getZanr());
        t.setGodinaIzdavanja(dto.getGodinaIzdavanja());
        t.setIzdavac(dto.getIzdavac());
        t.setBrojPrimeraka(dto.getBrojPrimeraka());
        return repository.save(t);
    }
    public void potvrdiTrebovanje(Long id) {
        TrebovanjeKnjige trebovanje = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trebovanje nije pronađeno"));
        
        trebovanje.setStatus("POTVRDJENO"); // ili true ako je boolean
        repository.save(trebovanje);
    }
}