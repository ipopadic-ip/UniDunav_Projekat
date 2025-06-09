package com.unidunav.sluzbenik.service;

import com.unidunav.sluzbenik.dto.PotvrdaDTO;
import com.unidunav.sluzbenik.model.Potvrda;
import com.unidunav.sluzbenik.repository.PotvrdaRepository;
import com.unidunav.student.model.Student;
import com.unidunav.student.repository.StudentRepository;
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
    
    @Autowired
    private StudentRepository studentRepository;
    
    

    @Override
    public PotvrdaDTO izdajPotvrdu(PotvrdaDTO dto) {
        // Pronađi studenta preko ID-ja
        Student student = studentRepository.findById(dto.getStudentId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student nije pronađen"));

        // Proveri da li korisnik ima rolu STUDENT
        boolean jeStudent = student.getUser().getRoles().stream()
            .map(Role::getNaziv)
            .anyMatch("STUDENT"::equalsIgnoreCase);

        if (!jeStudent) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Korisnik nema studentsku rolu");
        }

        // Kreiraj novu potvrdu
        Potvrda p = new Potvrda();
        p.setTip(dto.getTip());
        p.setTekst(dto.getTekst());
        p.setDatumIzdavanja(LocalDate.now());
        p.setStudent(student);  // ✔️ Sad je pravi entitet

        return toDTO(repo.save(p));
    }
    @Override
    public PotvrdaDTO createPotvrda(PotvrdaDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
            .orElseThrow(() -> new RuntimeException("Student nije pronađen"));
        User user = student.getUser();

        Potvrda potvrda = new Potvrda();
        potvrda.setTip(dto.getTip());
        potvrda.setTekst(dto.getTekst());
        potvrda.setStudent(student); // automatski će se setovati datum preko @PrePersist

        Potvrda saved = repo.save(potvrda);

        PotvrdaDTO response = new PotvrdaDTO();
        response.setId(saved.getId());
        response.setTip(saved.getTip());
        response.setTekst(saved.getTekst());
        response.setDatumIzdavanja(saved.getDatumIzdavanja());
        response.setStudentId(student.getId());
        response.setStudentIme(user.getIme() + " " + user.getPrezime());
        response.setBrojIndeksa(student.getBrojIndeksa());

        return response;
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
        

      
        User user = p.getStudent().getUser();
        dto.setStudentIme(user.getIme() + " " + user.getPrezime());
        dto.setBrojIndeksa(p.getStudent().getBrojIndeksa());
        return dto;
    }
    @Override
    public List<PotvrdaDTO> getAllPotvrde() {
        return repo.findAll().stream().map(p -> {
            Student student = p.getStudent();
            User user = student.getUser();

            PotvrdaDTO dto = new PotvrdaDTO();
            dto.setId(p.getId());
            dto.setTip(p.getTip());
            dto.setTekst(p.getTekst());
            dto.setDatumIzdavanja(p.getDatumIzdavanja());
            dto.setStudentId(student.getId());
            dto.setStudentIme(user.getIme() + " " + user.getPrezime());
            dto.setBrojIndeksa(student.getBrojIndeksa());
            return dto;
        }).collect(Collectors.toList());
    }

}