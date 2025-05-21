package com.unidunav.studijskiProgram.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.studijskiProgram.dto.StudijskiProgramDTO;
import com.unidunav.studijskiProgram.service.StudijskiProgramService;

@RestController
@RequestMapping("/api/studijskiprogrami")
public class StudijskiProgramController {

    private final StudijskiProgramService studijskiProgramService;

    public StudijskiProgramController(StudijskiProgramService studijskiProgramService) {
        this.studijskiProgramService = studijskiProgramService;
    }

    @GetMapping
    public List<StudijskiProgramDTO> getAll() {
        return studijskiProgramService.findAll();
    }

    @GetMapping("/{id}")
    public StudijskiProgramDTO getById(@PathVariable Long id) {
        return studijskiProgramService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public StudijskiProgramDTO create(@RequestBody StudijskiProgramDTO dto) {
        return studijskiProgramService.create(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public StudijskiProgramDTO update(@PathVariable Long id, @RequestBody StudijskiProgramDTO dto) {
        return studijskiProgramService.update(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studijskiProgramService.delete(id);
    }
}
