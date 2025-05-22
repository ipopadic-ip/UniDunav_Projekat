package com.unidunav.predmet.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.service.PredmetService;

import java.util.List;

@RestController
@RequestMapping("/api/predmeti")
@CrossOrigin(origins = "*")
public class PredmetController {

    @Autowired
    private PredmetService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PredmetDTO create(@RequestBody PredmetDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<PredmetDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PredmetDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PredmetDTO update(@PathVariable Long id, @RequestBody PredmetDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
