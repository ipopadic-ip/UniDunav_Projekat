package com.unidunav.profesor.controller;

import com.unidunav.profesor.dto.ProfesorDTO;
import com.unidunav.profesor.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesor")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
public class ProfesorController {

    @Autowired
    private ProfesorService service;

    @PostMapping
    public ProfesorDTO create(@RequestBody ProfesorDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ProfesorDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProfesorDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ProfesorDTO update(@PathVariable Long id, @RequestBody ProfesorDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
