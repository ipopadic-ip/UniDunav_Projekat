package com.unidunav.studijskiProgram.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.studijskiProgram.dto.StudijskiProgramDTO;
import com.unidunav.studijskiProgram.service.StudijskiProgramService;

@RestController
@RequestMapping("/api/studijskiprogrami")
@CrossOrigin(origins = "*")
public class StudijskiProgramController {

    private final StudijskiProgramService studijskiProgramService;

    public StudijskiProgramController(StudijskiProgramService studijskiProgramService) {
        this.studijskiProgramService = studijskiProgramService;
    }
    
    @GetMapping("/{id}/predmeti-po-godinama")
    public Map<Integer, List<PredmetDTO>> getPredmetiPoGodinama(@PathVariable Long id) {
        return studijskiProgramService.getPredmetiPoGodinama(id);
    }
    
    @GetMapping("/katedra/{katedraId}/grupisani-po-tipu")
    public Map<String, List<StudijskiProgramDTO>> getByKatedraGrouped(@PathVariable Long katedraId) {
        return studijskiProgramService.findByKatedraGroupedByTipStudija(katedraId);
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
