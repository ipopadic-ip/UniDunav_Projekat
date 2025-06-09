package com.unidunav.katedra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.katedra.dto.KatedraCreateUpdateDTO;
import com.unidunav.katedra.dto.KatedraDTO;
import com.unidunav.katedra.service.KatedraService;

@RestController
@RequestMapping("/api/katedre")
@CrossOrigin(origins = "*")
public class KatedraController {

    private final KatedraService katedraService;

    public KatedraController(KatedraService katedraService) {
        this.katedraService = katedraService;
    }

//    @GetMapping
//    public ResponseEntity<List<KatedraDTO>> getAll() {
//        return ResponseEntity.ok(katedraService.findAll());
//    }
    
    // 1. Samo aktivne katedre
    @GetMapping
    public ResponseEntity<List<KatedraDTO>> getAll() {
        return ResponseEntity.ok(katedraService.findAllAktivne());
    }

    // 2. Sve katedre za admina (aktivne pa obrisane, sortirane po fakultetu)
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<KatedraDTO>> getAllAdmin() {
        return ResponseEntity.ok(katedraService.findAllAdmin());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KatedraDTO> getById(@PathVariable Long id) {
        KatedraDTO dto = katedraService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
    
    @GetMapping("/departman/{departmanId}")
    public ResponseEntity<List<KatedraDTO>> getByDepartmanId(@PathVariable Long departmanId) {
        return ResponseEntity.ok(katedraService.findAktivneByDepartmanId(departmanId));
    }
//    public ResponseEntity<List<KatedraDTO>> getByDepartmanId(@PathVariable Long departmanId) {
//        return ResponseEntity.ok(katedraService.findByDepartmanId(departmanId));
//    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KatedraDTO> create(@RequestBody KatedraCreateUpdateDTO dto) {
        return ResponseEntity.ok(katedraService.create(dto));
    }
//    public ResponseEntity<KatedraDTO> create(@RequestBody KatedraDTO dto) {
//        KatedraDTO created = katedraService.create(dto);
//        return ResponseEntity.ok(created);
//    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KatedraDTO> update(@PathVariable Long id, @RequestBody KatedraCreateUpdateDTO dto) {
        return ResponseEntity.ok(katedraService.update(id, dto));
    }
//    public ResponseEntity<KatedraDTO> update(@PathVariable Long id, @RequestBody KatedraDTO dto) {
//        KatedraDTO updated = katedraService.update(id, dto);
//        if (updated == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        katedraService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
 // 3. Deaktivacija
    @PatchMapping("/{id}/deaktiviraj")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deaktiviraj(@PathVariable Long id) {
        katedraService.setDeleted(id, true);
        return ResponseEntity.ok().build();
    }

    // 4. Aktivacija
    @PatchMapping("/{id}/aktiviraj")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> aktiviraj(@PathVariable Long id) {
        katedraService.setDeleted(id, false);
        return ResponseEntity.ok().build();
    }
}
