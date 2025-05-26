package com.unidunav.profesorPredmet.service;

import com.unidunav.predmet.model.Predmet;
import com.unidunav.predmet.repository.PredmetRepository;
import com.unidunav.profesor.model.Profesor;
import com.unidunav.profesor.repository.ProfesorRepository;
import com.unidunav.profesorPredmet.dto.ProfesorPredmetDTO;
import com.unidunav.profesorPredmet.dto.ProfesorPredmetResponseDTO;
import com.unidunav.profesorPredmet.model.ProfesorPredmet;
import com.unidunav.profesorPredmet.repository.ProfesorPredmetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorPredmetService {
    private final ProfesorPredmetRepository repository;
    private final ProfesorRepository profesorRepo;
    private final PredmetRepository predmetRepo;

    public ProfesorPredmetService(ProfesorPredmetRepository repository, ProfesorRepository profesorRepo, PredmetRepository predmetRepo) {
        this.repository = repository;
        this.profesorRepo = profesorRepo;
        this.predmetRepo = predmetRepo;
    }

    public ProfesorPredmetResponseDTO dodaj(ProfesorPredmetDTO dto) {
        Profesor profesor = profesorRepo.findById(dto.getProfesorId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));

        Predmet predmet = predmetRepo.findById(dto.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));

        ProfesorPredmet pp = new ProfesorPredmet();
        pp.setProfesor(profesor);
        pp.setPredmet(predmet);

        ProfesorPredmet sacuvan = repository.save(pp);

        ProfesorPredmetResponseDTO res = new ProfesorPredmetResponseDTO();
        res.setId(sacuvan.getId());
        res.setProfesorId(profesor.getId());
//        res.setProfesorIme(profesor.getIme() + " " + profesor.getPrezime());
        res.setPredmetId(predmet.getId());
        res.setPredmetNaziv(predmet.getNaziv());

        return res;
    }

    public List<ProfesorPredmetResponseDTO> sviZaProfesora(Long profesorId) {
        return repository.findByProfesorId(profesorId).stream().map(pp -> {
            ProfesorPredmetResponseDTO dto = new ProfesorPredmetResponseDTO();
            dto.setId(pp.getId());
            dto.setProfesorId(pp.getProfesor().getId());
//            dto.setProfesorIme(pp.getProfesor().getIme() + " " + pp.getProfesor().getPrezime());
            dto.setPredmetId(pp.getPredmet().getId());
            dto.setPredmetNaziv(pp.getPredmet().getNaziv());
            return dto;
        }).collect(Collectors.toList());
    }
    
    public ProfesorPredmetResponseDTO izmeni(Long id, ProfesorPredmetDTO dto) {
        ProfesorPredmet pp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veza profesor-predmet nije pronađena"));

        Profesor profesor = profesorRepo.findById(dto.getProfesorId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));

        Predmet predmet = predmetRepo.findById(dto.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));

        pp.setProfesor(profesor);
        pp.setPredmet(predmet);

        ProfesorPredmet sacuvan = repository.save(pp);

        ProfesorPredmetResponseDTO res = new ProfesorPredmetResponseDTO();
        res.setId(sacuvan.getId());
        res.setProfesorId(profesor.getId());
//        res.setProfesorIme(profesor.getIme() + " " + profesor.getPrezime());
        res.setPredmetId(predmet.getId());
        res.setPredmetNaziv(predmet.getNaziv());

        return res;
    }

    public void obrisi(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Veza profesor-predmet nije pronađena");
        }
        repository.deleteById(id);
    }

}

