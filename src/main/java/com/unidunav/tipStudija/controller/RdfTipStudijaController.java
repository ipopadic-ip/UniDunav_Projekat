package com.unidunav.tipStudija.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.tipStudija.dto.TipStudijaDTO;
import com.unidunav.tipStudija.service.RdfTipStudijaService;

import java.util.List;

@RestController
@RequestMapping("/api/rdf/tipovistudija")
@CrossOrigin(origins = "*")
public class RdfTipStudijaController {

    private final RdfTipStudijaService rdfTipStudijaService;

    public RdfTipStudijaController(RdfTipStudijaService rdfTipStudijaService) {
        this.rdfTipStudijaService = rdfTipStudijaService;
    }

//    @GetMapping
//    public ResponseEntity<List<String>> getAll() {
//        return ResponseEntity.ok(rdfTipStudijaService.findAll());
//    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Void> updateTipStudija(@RequestParam String stariTip, @RequestParam String noviTip) {
        rdfTipStudijaService.updateTipStudija(stariTip, noviTip);
        return ResponseEntity.ok().build();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-by-uri")
    public ResponseEntity<Void> updateByUri(@RequestParam String uri, @RequestParam String noviTip) {
        rdfTipStudijaService.updateTipStudijaByUri(uri, noviTip);
        return ResponseEntity.ok().build();
    }


    
//    @GetMapping
//    public ResponseEntity<List<String>> getAll() {
//        return ResponseEntity.ok(rdfTipStudijaService.findAll());
//    }
    
    @GetMapping
    public ResponseEntity<List<TipStudijaDTO>> getAllActiveDto() {
        return ResponseEntity.ok(rdfTipStudijaService.findAllActiveDto());
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<List<String>> getAllAdmin() {
        return ResponseEntity.ok(rdfTipStudijaService.findAllAdmin());
    }
    
    @GetMapping("/admin/dto")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TipStudijaDTO>> getAllAdminDto() {
        return ResponseEntity.ok(rdfTipStudijaService.findAllAdminDto());
    }

    
//    @GetMapping("/by-naziv")
//    public ResponseEntity<String> getByNaziv(@RequestParam String tip) {
//        return ResponseEntity.ok(rdfTipStudijaService.findById(tip));
//    }
    @GetMapping("/by-naziv")
    public ResponseEntity<String> getByNaziv(@RequestParam String tip) {
        try {
            String found = rdfTipStudijaService.findById(tip);
            return ResponseEntity.ok(found);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @GetMapping("/{tip}")
    public ResponseEntity<String> getById(@PathVariable String tip) {
        return ResponseEntity.ok(rdfTipStudijaService.findById(tip));
    }

//    PUT /api/rdf/tipovistudija/Ilijine Studije/deaktiviraj
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{tip}/deaktiviraj")
    public ResponseEntity<Void> deaktiviraj(@PathVariable String tip) {
        rdfTipStudijaService.deaktiviraj(tip);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{tip}/aktiviraj")
    public ResponseEntity<Void> aktiviraj(@PathVariable String tip) {
        rdfTipStudijaService.aktiviraj(tip);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> create(@RequestParam String tip) {
        rdfTipStudijaService.saveTipStudija(tip);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@RequestParam String tip) {
        rdfTipStudijaService.deleteTipStudija(tip);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/by-uri")
    public ResponseEntity<TipStudijaDTO> getByUri(@RequestParam String uri) {
    	return ResponseEntity.ok(rdfTipStudijaService.findByUri(uri));
    }
    
    @GetMapping("/pretraga")
    public ResponseEntity<List<TipStudijaDTO>> pretraziPoTipu(@RequestParam String query) {
        List<TipStudijaDTO> rezultati = rdfTipStudijaService.searchByTip(query);
        return ResponseEntity.ok(rezultati);
    }
    
    @GetMapping("/pretraga-deleted")
    public ResponseEntity<List<TipStudijaDTO>> pretraziPoDeleted(@RequestParam String query) {
        List<TipStudijaDTO> rezultati = rdfTipStudijaService.searchByDeleted(query);
        return ResponseEntity.ok(rezultati);
    }



}
