package com.unidunav.sluzbenik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unidunav.sluzbenik.dto.IzdavanjeKnjigeRequestDTO;
import com.unidunav.sluzbenik.dto.IznajmljivanjeKnjigeDTO;
import com.unidunav.sluzbenik.dto.KnjigaDTO;
import com.unidunav.sluzbenik.dto.KnjigaIzdavanjeDTO;
import com.unidunav.sluzbenik.dto.VracanjeKnjigeRequestDTO;
import com.unidunav.sluzbenik.service.BibliotekaService;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/biblioteka")
@CrossOrigin(origins = "*")
public class BibliotekaController {

    @Autowired
    private BibliotekaService bibliotekaService;

    // 1. Pretraga dostupnih knjiga po nazivu
    @GetMapping("/knjige")
    public ResponseEntity<List<KnjigaDTO>> pretraziKnjige(@RequestParam(required = false, defaultValue = "") String naziv) {
        List<KnjigaDTO> knjige = bibliotekaService.pretraziDostupneKnjige(naziv);
        return ResponseEntity.ok(knjige);
    }

    // 2. Izdavanje primerka knjige studentu
    @PostMapping("/izdaj")
    public ResponseEntity<Void> izdajKnjigu(@RequestBody IzdavanjeKnjigeRequestDTO dto) {
        bibliotekaService.izdajKnjigu(dto.getPrimerakId(), dto.getBrojIndeksa());
        return ResponseEntity.ok().build();
    }

    // 3. Dohvatanje svih aktivnih iznajmljivanja studenta po broju indeksa
    @GetMapping("/student/{indeks}/iznajmljivanja")
    public ResponseEntity<List<IznajmljivanjeKnjigeDTO>> iznajmljivanjaStudenta(@PathVariable String indeks) {
        List<IznajmljivanjeKnjigeDTO> lista = bibliotekaService.iznajmljivanjaZaStudenta(indeks);
        return ResponseEntity.ok(lista);
    }

    // 4. Vraćanje iznajmljene knjige
    @PostMapping("/vrati")
    public ResponseEntity<Void> vratiKnjigu(@RequestBody VracanjeKnjigeRequestDTO dto) {
        bibliotekaService.vratiKnjigu(dto.getIznajmljivanjeId());
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/student/{indeks}/knjige")
    public ResponseEntity<List<KnjigaIzdavanjeDTO>> dostupneKnjigeZaStudenta(@PathVariable String indeks) {
        return ResponseEntity.ok(bibliotekaService.pretraziDostupneKnjigeZaStudenta(indeks));
    }
}