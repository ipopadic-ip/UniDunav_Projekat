package com.unidunav.sluzbenik.controller;

import com.unidunav.sluzbenik.model.Knjiga;
import com.unidunav.sluzbenik.service.KnjigaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knjige")
public class KnjigaController {

    private final KnjigaService knjigaService;

    public KnjigaController(KnjigaService knjigaService) {
        this.knjigaService = knjigaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<Knjiga> dodajKnjigu(@RequestBody Knjiga knjiga) {
        return ResponseEntity.ok(knjigaService.kreirajKnjigu(knjiga));
    }

    @GetMapping
    public ResponseEntity<List<Knjiga>> sveKnjige() {
        return ResponseEntity.ok(knjigaService.sveKnjige());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Knjiga> nadjiKnjigu(@PathVariable Long id) {
        return ResponseEntity.ok(knjigaService.nadjiPoId(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<Void> obrisiKnjigu(@PathVariable Long id) {
        knjigaService.obrisiKnjigu(id);
        return ResponseEntity.noContent().build();
    }
}
