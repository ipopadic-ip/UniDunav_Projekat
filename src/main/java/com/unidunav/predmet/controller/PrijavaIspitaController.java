package com.unidunav.predmet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.predmet.dto.PohadjanjePredmetaDTO;
import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.dto.PrijavaIspitaDTO;
import com.unidunav.predmet.service.prijavaIspita.PrijavaIspitaService;
import com.unidunav.predmet.service.prijavaIspita.PrijavaIspitaServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/api/prijave-ispita")
@CrossOrigin(origins = "*")
public class PrijavaIspitaController {
	
	   private final PrijavaIspitaService prijavaIspitaService;

	    @Autowired
	    public PrijavaIspitaController(PrijavaIspitaService prijavaIspitaService) {
	        this.prijavaIspitaService = prijavaIspitaService;
	    }

    @Autowired
    private PrijavaIspitaService service;

    @Autowired
    private PrijavaIspitaServiceImpl prijavaService;

    // Standardni CRUD

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PrijavaIspitaDTO create(@RequestBody PrijavaIspitaDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<PrijavaIspitaDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PrijavaIspitaDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PrijavaIspitaDTO update(@PathVariable Long id, @RequestBody PrijavaIspitaDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Custom funkcionalnosti za studenta

    // 🔹 Vraća predmete koje student može da prijavi
    @GetMapping("/dostupne/{studentId}")
    public ResponseEntity<List<PrijavaIspitaDTO>> getDostupnePrijave(@PathVariable Long studentId) {
        return ResponseEntity.ok(prijavaIspitaService.getDostupnePrijave(studentId));
    }

    @PostMapping("/prijavi/{prijavaId}")
    public ResponseEntity<PrijavaIspitaDTO> prijavi(@PathVariable Long prijavaId) {
        return ResponseEntity.ok(prijavaIspitaService.prijaviIspit(prijavaId));
    }
}