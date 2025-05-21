package com.unidunav.profesorPredmet.service;

import com.unidunav.profesorPredmet.dto.TerminNastaveDTO;
import com.unidunav.profesorPredmet.dto.TerminNastaveResponseDTO;
import com.unidunav.profesorPredmet.model.ProfesorPredmet;
import com.unidunav.profesorPredmet.model.TerminNastave;
import com.unidunav.profesorPredmet.repository.ProfesorPredmetRepository;
import com.unidunav.profesorPredmet.repository.TerminNastaveRepository;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TerminNastaveService {

    private final TerminNastaveRepository repo;
    private final ProfesorPredmetRepository profesorPredmetRepo;
    private final UserRepository userRepo;

    public TerminNastaveService(TerminNastaveRepository repo, ProfesorPredmetRepository profesorPredmetRepo, UserRepository userRepo) {
        this.repo = repo;
        this.profesorPredmetRepo = profesorPredmetRepo;
        this.userRepo = userRepo;
    }

    public TerminNastaveResponseDTO kreiraj(TerminNastaveDTO dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User autor = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        ProfesorPredmet pp = profesorPredmetRepo.findById(dto.getProfesorPredmetId())
                .orElseThrow(() -> new RuntimeException("ProfesorPredmet nije pronađen"));

        TerminNastave termin = new TerminNastave();
        termin.setTerminPocetka(dto.getTerminPocetka());
        termin.setTerminZavrsetka(dto.getTerminZavrsetka());
        termin.setProfesorPredmet(pp);
        termin.setAutor(autor);

        TerminNastave sacuvan = repo.save(termin);

        return mapToDTO(sacuvan);
    }

    public List<TerminNastaveResponseDTO> sviZaProfesorPredmet(Long id) {
        return repo.findByProfesorPredmetId(id).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TerminNastaveResponseDTO azuriraj(Long id, TerminNastaveDTO dto) {
        TerminNastave termin = repo.findById(id).orElseThrow(() -> new RuntimeException("Termin nije pronađen"));
        termin.setTerminPocetka(dto.getTerminPocetka());
        termin.setTerminZavrsetka(dto.getTerminZavrsetka());
        return mapToDTO(repo.save(termin));
    }

    public void obrisi(Long id) {
        repo.deleteById(id);
    }

    private TerminNastaveResponseDTO mapToDTO(TerminNastave termin) {
        TerminNastaveResponseDTO dto = new TerminNastaveResponseDTO();
        dto.setId(termin.getId());
        dto.setTerminPocetka(termin.getTerminPocetka());
        dto.setTerminZavrsetka(termin.getTerminZavrsetka());
        dto.setProfesorPredmetId(termin.getProfesorPredmet().getId());
        dto.setAutorIme(termin.getAutor().getIme() + " " + termin.getAutor().getPrezime());
        return dto;
    }
}
