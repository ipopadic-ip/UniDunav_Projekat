package com.unidunav.sluzbenik.controller;

import com.unidunav.sluzbenik.dto.SluzbenikDTO;
import com.unidunav.sluzbenik.service.SluzbenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sluzbenik")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'SLUZBENIK')")
public class SluzbenikController {

    @Autowired
    private SluzbenikService service;

    @PostMapping
    public SluzbenikDTO create(@RequestBody SluzbenikDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<SluzbenikDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SluzbenikDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public SluzbenikDTO update(@PathVariable Long id, @RequestBody SluzbenikDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
