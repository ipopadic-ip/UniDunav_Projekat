package com.unidunav.sluzbenik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unidunav.sluzbenik.dto.IzdavanjeKnjigeRequestDTO;
import com.unidunav.sluzbenik.dto.IznajmljivanjeKnjigeDTO;
import com.unidunav.sluzbenik.dto.KnjigaDTO;
import com.unidunav.sluzbenik.dto.KnjigaIzdavanjeDTO;
import com.unidunav.sluzbenik.dto.PrimerakKnjigeDTO;
import com.unidunav.sluzbenik.dto.VracanjeKnjigeRequestDTO;
import com.unidunav.sluzbenik.service.BibliotekaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

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
    @PostMapping("/knjige")
    @PreAuthorize("hasRole('KOMERCIJALISTA')")
    public ResponseEntity<KnjigaDTO> dodajKnjigu(@RequestBody KnjigaDTO dto) {
        KnjigaDTO nova = bibliotekaService.dodajKnjigu(dto);
        return new ResponseEntity<>(nova, HttpStatus.CREATED);
    }
    @PostMapping("/primerci")
    @PreAuthorize("hasRole('KOMERCIJALISTA')")
    public ResponseEntity<PrimerakKnjigeDTO> dodajPrimerak(@RequestBody PrimerakKnjigeDTO dto) {
        return new ResponseEntity<>(bibliotekaService.dodajPrimerak(dto), HttpStatus.CREATED);
    }

    @GetMapping("/primerci/{knjigaId}")
    @PreAuthorize("hasRole('KOMERCIJALISTA')")
    public ResponseEntity<List<PrimerakKnjigeDTO>> getPrimerciZaKnjigu(@PathVariable Long knjigaId) {
        return ResponseEntity.ok(bibliotekaService.getPrimerciZaKnjigu(knjigaId));
    }
    @DeleteMapping("/primerak/{isbn}")
    public ResponseEntity<Void> obrisiPrimerakPoIsbn(@PathVariable String isbn) {
        bibliotekaService.obrisiPrimerakPoIsbn(isbn);
        return ResponseEntity.noContent().build();
    }
}