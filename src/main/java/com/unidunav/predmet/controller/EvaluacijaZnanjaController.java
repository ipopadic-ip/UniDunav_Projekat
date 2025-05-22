package com.unidunav.predmet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.predmet.dto.EvaluacijaZnanjaDTO;
import com.unidunav.predmet.service.evaluacijaZnanja.EvaluacijaZnanjaService;

import java.util.List;

@RestController
@RequestMapping("/api/evaluacija-znanja")
@CrossOrigin(origins = "*")
public class EvaluacijaZnanjaController {

    @Autowired
    private EvaluacijaZnanjaService service;

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
}
