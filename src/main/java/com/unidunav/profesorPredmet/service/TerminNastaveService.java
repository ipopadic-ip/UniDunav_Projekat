package com.unidunav.profesorPredmet.service;

import com.unidunav.predmet.repository.PredmetRepository;
import com.unidunav.profesor.repository.ProfesorRepository;
import com.unidunav.profesorPredmet.dto.TerminNastaveDTO;
import com.unidunav.profesorPredmet.dto.TerminNastaveResponseDTO;
import com.unidunav.profesorPredmet.model.Ishod;
import com.unidunav.profesorPredmet.model.ProfesorPredmet;
import com.unidunav.profesorPredmet.model.TerminNastave;
import com.unidunav.profesorPredmet.repository.IshodRepository;
import com.unidunav.profesorPredmet.repository.ProfesorPredmetRepository;
import com.unidunav.profesorPredmet.repository.TerminNastaveRepository;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TerminNastaveService {

    @Autowired
    private TerminNastaveRepository terminRepo;

    @Autowired
    private ProfesorPredmetRepository profesorPredmetRepo;

    public List<TerminNastaveDTO> findAllByProfesor(Long profesorId) {
        return terminRepo.findByProfesorPredmetProfesorId(profesorId)
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    public TerminNastaveDTO create(TerminNastaveDTO dto) {
        TerminNastave termin = new TerminNastave();
        termin.setTerminPocetka(dto.getTerminPocetka());
        termin.setTerminZavrsetka(dto.getTerminZavrsetka());
        termin.setIshod(dto.getIshod());

        ProfesorPredmet profPred = profesorPredmetRepo.findById(dto.getProfesorPredmetId())
            .orElseThrow(() -> new RuntimeException("Veza profesor-predmet nije pronađena"));
        termin.setProfesorPredmet(profPred);

        termin = terminRepo.save(termin);
        return mapToDTO(termin);
    }

    public TerminNastaveDTO azuriraj(Long id, TerminNastaveDTO dto) {
        TerminNastave termin = terminRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Termin nije pronađen"));

        termin.setTerminPocetka(dto.getTerminPocetka());
        termin.setTerminZavrsetka(dto.getTerminZavrsetka());
        termin.setIshod(dto.getIshod());

        ProfesorPredmet profPred = profesorPredmetRepo.findById(dto.getProfesorPredmetId())
            .orElseThrow(() -> new RuntimeException("Veza profesor-predmet nije pronađena"));
        termin.setProfesorPredmet(profPred);

        termin = terminRepo.save(termin);
        return mapToDTO(termin);
    }

    public void obrisi(Long id) {
        if (!terminRepo.existsById(id)) {
            throw new RuntimeException("Termin ne postoji");
        }
        terminRepo.deleteById(id);
    }

    private TerminNastaveDTO mapToDTO(TerminNastave t) {
        TerminNastaveDTO dto = new TerminNastaveDTO();
        dto.setId(t.getId());
        dto.setTerminPocetka(t.getTerminPocetka());
        dto.setTerminZavrsetka(t.getTerminZavrsetka());
        dto.setProfesorPredmetId(t.getProfesorPredmet().getId());
        dto.setIshod(t.getIshod());
        return dto;
    }
}