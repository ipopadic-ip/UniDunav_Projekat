package com.unidunav.sluzbenik.service;

import com.unidunav.sluzbenik.model.Knjiga;
import com.unidunav.sluzbenik.repository.KnjigaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnjigaService {

    private final KnjigaRepository knjigaRepository;

    public KnjigaService(KnjigaRepository knjigaRepository) {
        this.knjigaRepository = knjigaRepository;
    }

    public Knjiga kreirajKnjigu(Knjiga knjiga) {
        knjiga.setBrojDostupnih(knjiga.getBrojPrimeraka()); // inicijalno sve dostupne
        return knjigaRepository.save(knjiga);
    }

    public List<Knjiga> sveKnjige() {
        return knjigaRepository.findAll();
    }

    public Knjiga nadjiPoId(Long id) {
        return knjigaRepository.findById(id).orElseThrow(() -> new RuntimeException("Knjiga nije pronađena"));
    }

    public void obrisiKnjigu(Long id) {
        knjigaRepository.deleteById(id);
    }
}
