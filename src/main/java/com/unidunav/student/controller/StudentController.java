package com.unidunav.student.controller;

import com.unidunav.obavestenje.dto.ObavestenjeStudentuDTO;
import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.dto.StudentIstorijaStudiranjaResponseDTO;
import com.unidunav.predmet.service.pohadjanjePredmeta.PohadjanjePredmetaService;
import com.unidunav.predmet.service.pohadjanjePredmeta.PohadjanjePredmetaServiceImpl;
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
@PreAuthorize("hasAnyRole('ADMIN', 'STUDENT','SLUZBENIK')")
public class StudentController {

    @Autowired
    private StudentService service;
    
    @PreAuthorize("permitAll()")
    @GetMapping("/{studentId}/predmeti")
    public ResponseEntity<List<PredmetDTO>> getPredmetiKojeStudentSlusa(@PathVariable Long studentId) {
        List<PredmetDTO> predmeti = service.getPredmetiKojeStudentSlusa(studentId);
        return ResponseEntity.ok(predmeti);
    }

    @PostMapping
    public StudentDTO create(@RequestBody StudentDTO dto) {
        return service.create(dto);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/pretraga")
    public List<StudentDTO> pretraziPoIndeksu(@RequestParam String indeks) {
        return service.findByBrojIndeksa(indeks);
    }
    
    @GetMapping("/{studentId}/obavestenja")
    public ResponseEntity<List<ObavestenjeStudentuDTO>> getObavestenja(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getObavestenjaZaStudenta(studentId));
    }

    @GetMapping
    public List<StudentDTO> getAll() {
        return service.findAll();
    }
    
  
    private final PohadjanjePredmetaServiceImpl pohadjanjeService;

    public StudentController(PohadjanjePredmetaServiceImpl pohadjanjeService) {
        this.pohadjanjeService = pohadjanjeService;
    }

    @GetMapping("/{studentId}/istorija")
    public ResponseEntity<StudentIstorijaStudiranjaResponseDTO> getIstorijaStudiranja(@PathVariable Long studentId) {
        StudentIstorijaStudiranjaResponseDTO dto = pohadjanjeService.getIstorijaStudiranjaZaStudenta(studentId);
        return ResponseEntity.ok(dto);
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