package com.unidunav.predmet.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.unidunav.predmet.dto.EvaluacijaZnanjaCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.predmet.dto.EvaluacijaZnanjaDTO;
import com.unidunav.predmet.model.PohadjanjePredmeta;
import com.unidunav.predmet.model.TipEvaluacije;
import com.unidunav.predmet.repository.TipEvaluacijeRepository;
import com.unidunav.predmet.service.evaluacijaZnanja.EvaluacijaZnanjaService;
import com.unidunav.predmet.service.pohadjanjePredmeta.PohadjanjePredmetaService;
import com.unidunav.student.model.Student;
import com.unidunav.utils.PdfGeneratorUtil;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/evaluacija-znanja")
@CrossOrigin(origins = "http://localhost:4200")
public class EvaluacijaZnanjaController {

    @Autowired
    private EvaluacijaZnanjaService service;
    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;
    @Autowired
    private TipEvaluacijeRepository tipRepo;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public EvaluacijaZnanjaDTO create(@RequestBody EvaluacijaZnanjaDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<EvaluacijaZnanjaDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EvaluacijaZnanjaDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public EvaluacijaZnanjaDTO update(@PathVariable Long id, @RequestBody EvaluacijaZnanjaDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @PostMapping("/kreiraj")
    public ResponseEntity<Void> kreirajEvaluacije(@RequestBody EvaluacijaZnanjaCreateDTO dto) {
        service.kreirajEvaluacijeZaPredmet(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EvaluacijaZnanjaDTO>> getEvaluacijeForStudent(@PathVariable Long studentId) {
        List<EvaluacijaZnanjaDTO> lista = service.getEvaluacijeForStudent(studentId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pdf/{evaluacijaId}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long evaluacijaId) {
        EvaluacijaZnanjaDTO evaluacija = service.findById(evaluacijaId);
        PohadjanjePredmeta pohadjanje = pohadjanjePredmetaService.findById2(evaluacija.getPohadjanjeId());

        String nazivTipaEvaluacije = tipRepo.findById(evaluacija.getTipEvaluacijeId())
            .map(TipEvaluacije::getTip)
            .orElse("Nepoznat tip");

        Student student = pohadjanje.getStudent();

        ByteArrayInputStream bis = PdfGeneratorUtil.generateEvaluacijaPdf(
            evaluacija, pohadjanje, nazivTipaEvaluacije, student
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=evaluacija_" + evaluacijaId + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }
}
