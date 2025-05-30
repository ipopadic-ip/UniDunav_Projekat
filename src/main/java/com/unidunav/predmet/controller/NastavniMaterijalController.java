package com.unidunav.predmet.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.unidunav.predmet.dto.NastavniMaterijalDTO;
import com.unidunav.predmet.service.nastavniMaterijal.NastavniMaterijalService;

@RestController
@RequestMapping("/api/nastavni-materijali")
@CrossOrigin(origins = "*")
public class NastavniMaterijalController {

    @Autowired
    private NastavniMaterijalService service;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('PROFESOR')")
    public NastavniMaterijalDTO upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("naziv") String naziv,
            @RequestParam("predmetId") Long predmetId
    ) throws IOException {
        return service.upload(file, naziv, predmetId);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public NastavniMaterijalDTO update(
            @PathVariable Long id,
            @RequestParam("naziv") String naziv,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        return service.update(id, naziv, file);
    }

    @GetMapping("/predmet/{predmetId}")
    public List<NastavniMaterijalDTO> getByPredmet(@PathVariable Long predmetId) {
        return service.findByPredmet(predmetId);
    }

    @GetMapping("/preuzmi/{id}")
    public ResponseEntity<Resource> preuzmi(@PathVariable Long id) {
        Resource resource = service.preuzmiMaterijal(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}