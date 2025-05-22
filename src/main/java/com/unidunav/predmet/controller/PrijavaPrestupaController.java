package com.unidunav.predmet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unidunav.predmet.dto.PrijavaPrestupaDTO;
import com.unidunav.predmet.service.prijavaPrestupa.PrijavaPrestupaService;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/prijava-prestupa")
@CrossOrigin(origins = "*")
@PreAuthorize("isAuthenticated()")
public class PrijavaPrestupaController {

    @Autowired
    private PrijavaPrestupaService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PrijavaPrestupaDTO create(@RequestBody PrijavaPrestupaDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<PrijavaPrestupaDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PrijavaPrestupaDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PrijavaPrestupaDTO update(@PathVariable Long id, @RequestBody PrijavaPrestupaDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
