package com.unidunav.dokument.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.unidunav.dokument.dto.TekstualnaVerzijaZaKreiranjeDTO;
import com.unidunav.dokument.dto.VerzijaDokumentaDTO;
import com.unidunav.dokument.model.VerzijaDokumenta;
import com.unidunav.dokument.service.VerzijaDokumentaService;

@RestController
@RequestMapping("/api/verzije")
@CrossOrigin(origins = "*")
public class VerzijaDokumentaController {

    @Autowired
    private VerzijaDokumentaService verzijaService;

    @GetMapping("/{dokumentId}")
    public List<VerzijaDokumentaDTO> sveVerzije(@PathVariable Long dokumentId) {
        return verzijaService.getSveAktivneVerzije(dokumentId);
    }
    
    @GetMapping("/admin")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public List<VerzijaDokumentaDTO> sveVerzijeAdminSve() {
        return verzijaService.getSveVerzijeAdminSve();
    }


    @GetMapping("/admin/{dokumentId}")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public List<VerzijaDokumentaDTO> sveVerzijeAdmin(@PathVariable Long dokumentId) {
        return verzijaService.getSveVerzijeAdmin(dokumentId);
    }
    
    @PostMapping("/{id}/tekst-verzija")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<VerzijaDokumentaDTO> dodajTekstualnuVerziju(
            @PathVariable Long id,
            @RequestBody TekstualnaVerzijaZaKreiranjeDTO dto) {

        VerzijaDokumenta verzija = verzijaService.kreirajTekstualnuVerziju(id, dto);
        VerzijaDokumentaDTO response = verzijaService.mapToDTO(verzija);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/{dokumentId}/upload")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<?> uploadVerzija(
            @PathVariable Long dokumentId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("email") String email
    ) {
        try {
            return ResponseEntity.ok(verzijaService.uploadVerzijuDokumenta(dokumentId, file, email));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Greška pri uploadu: " + e.getMessage());
        }
    }
    
    @PutMapping("/verzija/{id}")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<VerzijaDokumentaDTO> izmeniVerziju(
            @PathVariable Long id,
            @RequestBody VerzijaDokumentaDTO dto) {
        VerzijaDokumenta izmenjena = verzijaService.izmeniVerziju(id, dto);
        return ResponseEntity.ok(verzijaService.mapToDTO(izmenjena));
    }


    @PutMapping("/{id}/deaktiviraj")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<Void> obrisi(@PathVariable Long id) {
        verzijaService.obrisiVerziju(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reaktiviraj")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<Void> reaktiviraj(@PathVariable Long id) {
        verzijaService.reaktivirajVerziju(id);
        return ResponseEntity.ok().build();
    }
}