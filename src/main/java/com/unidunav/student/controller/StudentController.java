package com.unidunav.student.controller;

import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.student.dto.StudentDTO;
import com.unidunav.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
public class StudentController {

    @Autowired
    private StudentService service;
    
    
    @GetMapping("/{studentId}/predmeti")
    public ResponseEntity<List<PredmetDTO>> getPredmetiKojeStudentSlusa(@PathVariable Long studentId) {
        List<PredmetDTO> predmeti = service.getPredmetiKojeStudentSlusa(studentId);
        return ResponseEntity.ok(predmeti);
    }

    @PostMapping
    public StudentDTO create(@RequestBody StudentDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<StudentDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public StudentDTO update(@PathVariable Long id, @RequestBody StudentDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @PostMapping("/{id}/slika")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<String> uploadSlika(@PathVariable Long id, @RequestParam("slika") MultipartFile slika) {
        try {
            String putanja = service.uploadSlika(id, slika);
            return ResponseEntity.ok("Slika uspešno sačuvana: " + putanja);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greška: " + e.getMessage());
        }
    }
}
