package com.unidunav.dokument.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.unidunav.dokument.dto.DokumentDTO;
import com.unidunav.dokument.dto.TekstualnaVerzijaZaKreiranjeDTO;
import com.unidunav.dokument.dto.VerzijaDokumentaDTO;
import com.unidunav.dokument.model.Dokument;
import com.unidunav.dokument.model.VerzijaDokumenta;
import com.unidunav.dokument.service.DokumentService;

@RestController
@RequestMapping("/api/dokumenti")
@CrossOrigin(origins = "*")
public class DokumentController {

    @Autowired
    private DokumentService dokumentService;
    
    @PostMapping
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<Dokument> kreirajDokument(@RequestBody Dokument dokument) {
        return ResponseEntity.ok(dokumentService.dodajDokument(dokument));
    }

    // GET: samo aktivni dokumenti
    @GetMapping
    public ResponseEntity<List<Dokument>> sviAktivni() {
        return ResponseEntity.ok(dokumentService.sviAktivniDokumenti());
    }

    // GET: svi dokumenti (admin)
    @GetMapping("/admin")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<List<DokumentDTO>> sviZaAdmina() {
        List<Dokument> dokumenti = dokumentService.sviDokumentiZaAdmina();
        List<DokumentDTO> dtoList = dokumenti.stream()
            .map(dokumentService::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<DokumentDTO> izmeniDokument(@PathVariable Long id, @RequestBody DokumentDTO dto) {
        Dokument izmenjen = dokumentService.izmeniDokument(id, dto);
        return ResponseEntity.ok(dokumentService.toDto(izmenjen));
    }


    // Soft delete
    @PutMapping("/{id}/deaktiviraj")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<Void> obrisi(@PathVariable Long id) {
        dokumentService.obrisiDokument(id);
        return ResponseEntity.ok().build();
    }

    // Aktivacija
    @PutMapping("/{id}/aktiviraj")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<Void> aktiviraj(@PathVariable Long id) {
        dokumentService.aktivirajDokument(id);
        return ResponseEntity.ok().build();
    }


}

