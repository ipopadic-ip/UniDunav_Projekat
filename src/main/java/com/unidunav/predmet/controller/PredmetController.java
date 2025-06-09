package com.unidunav.predmet.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.service.PredmetService;
import com.unidunav.predmet.service.PredmetServiceImpl;

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

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public List<PredmetDTO> getAll() {
        return service.findAll();
    }
    
    @GetMapping
    public List<PredmetDTO> getAktivni() {
        return service.findAllAktivni();
    }

    @PutMapping("/{id}/aktiviraj")
    @PreAuthorize("hasAnyRole('ADMIN', 'SLUZBENIK')")
    public PredmetDTO aktiviraj(@PathVariable Long id) {
        return ((PredmetServiceImpl) service).aktiviraj(id);
    }

    @PutMapping("/{id}/deaktiviraj")
    @PreAuthorize("hasAnyRole('ADMIN', 'SLUZBENIK')")
    public PredmetDTO deaktiviraj(@PathVariable Long id) {
        return ((PredmetServiceImpl) service).deaktiviraj(id);
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
