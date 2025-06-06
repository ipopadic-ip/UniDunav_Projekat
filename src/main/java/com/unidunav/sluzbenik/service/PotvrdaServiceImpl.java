package com.unidunav.sluzbenik.service;

import com.unidunav.sluzbenik.dto.PotvrdaDTO;
import com.unidunav.sluzbenik.model.Potvrda;
import com.unidunav.sluzbenik.repository.PotvrdaRepository;
import com.unidunav.user.model.Role;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PotvrdaServiceImpl implements PotvrdaService {

    @Autowired
    private PotvrdaRepository repo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public PotvrdaDTO izdajPotvrdu(PotvrdaDTO dto) {
        // student mora da postoji i da ima rolu STUDENT
        User student = userRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student nije pronađen"));

        boolean jeStudent = student.getRoles().stream()
                .map(Role::getNaziv).anyMatch("STUDENT"::equalsIgnoreCase);

        if (!jeStudent) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Korisnik nije student");
        }

        Potvrda p = new Potvrda();
        p.setTip(dto.getTip());
        p.setTekst(dto.getTekst());
        p.setDatumIzdavanja(dto.getDatumIzdavanja() != null ? dto.getDatumIzdavanja() : LocalDate.now());
        p.setStudent(student);

        return toDTO(repo.save(p));
    }

    @Override
    public List<PotvrdaDTO> svePotvrde() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<PotvrdaDTO> potvrdeZaStudenta(Long studentId) {
        return repo.findByStudentId(studentId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ===== mapper =====
    private PotvrdaDTO toDTO(Potvrda p) {
        PotvrdaDTO dto = new PotvrdaDTO();
        dto.setId(p.getId());
        dto.setTip(p.getTip());
        dto.setTekst(p.getTekst());
        dto.setDatumIzdavanja(p.getDatumIzdavanja());
        dto.setStudentId(p.getStudent().getId());
        return dto;
    }
}
