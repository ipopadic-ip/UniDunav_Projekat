package com.unidunav.univerzitet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.unidunav.univerzitet.dto.UniverzitetDTO;
import com.unidunav.univerzitet.service.UniverzitetService;

@RestController
@RequestMapping("/api/univerziteti")
@CrossOrigin(origins = "*")
public class UniverzitetController {

    private final UniverzitetService univerzitetService;

    public UniverzitetController(UniverzitetService univerzitetService) {
        this.univerzitetService = univerzitetService;
    }

    @GetMapping
    public ResponseEntity<List<UniverzitetDTO>> getAll() {
        List<UniverzitetDTO> list = univerzitetService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniverzitetDTO> getById(@PathVariable Long id) {
        UniverzitetDTO dto = univerzitetService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UniverzitetDTO> create(@RequestBody UniverzitetDTO dto) {
        UniverzitetDTO created = univerzitetService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UniverzitetDTO> update(@PathVariable Long id, @RequestBody UniverzitetDTO dto) {
        UniverzitetDTO updated = univerzitetService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        univerzitetService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
//    @PostMapping("/{id}/slika")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> uploadSlika(@PathVariable Long id, @RequestParam("slika") MultipartFile slika) {
//        try {
//            String path = univerzitetService.uploadSlika(id, slika);
//            return ResponseEntity.ok("Slika sačuvana: " + path);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Greška: " + e.getMessage());
//        }
//    }
//    @PostMapping("/{id}/slika")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> uploadSlika(@PathVariable Long id, @RequestParam("slika") MultipartFile slika) {
//        try {
//            String path = univerzitetService.uploadSlika(id, slika);
//            return ResponseEntity.ok("Slika sačuvana: " + path);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Greška: " + e.getMessage());
//        }
//    }
    @PostMapping("/{id}/slike")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadSlike(@PathVariable Long id, @RequestParam("slike") List<MultipartFile> slike) {
        try {
            String poruka = univerzitetService.uploadSlike(id, slike);
            return ResponseEntity.ok(poruka);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Greška: " + e.getMessage());
        }
    }



}
