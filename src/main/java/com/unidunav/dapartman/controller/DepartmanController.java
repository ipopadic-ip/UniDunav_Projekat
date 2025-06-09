package com.unidunav.dapartman.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.dapartman.dto.DepartmanCreateUpdateDTO;
import com.unidunav.dapartman.dto.DepartmanDTO;
import com.unidunav.dapartman.service.DepartmanService;

@RestController
@RequestMapping("/api/departmani")
@CrossOrigin(origins = "*")
public class DepartmanController {

    private final DepartmanService departmanService;

    public DepartmanController(DepartmanService departmanService) {
        this.departmanService = departmanService;
    }

//    @GetMapping
//    public ResponseEntity<List<DepartmanDTO>> getAll() {
//        return ResponseEntity.ok(departmanService.findAll());
//    }
    
    // 1. Svi aktivni departmani
    @GetMapping
    public ResponseEntity<List<DepartmanDTO>> getAllActive() {
        return ResponseEntity.ok(departmanService.findAllActive());
    }

    // 2. Svi za admina (sortirani, uključuje i obrisane)
    @GetMapping("/admin")
    public ResponseEntity<List<DepartmanDTO>> getAllForAdmin() {
        return ResponseEntity.ok(departmanService.findAllForAdmin());
    }

    // 3. Deaktiviraj
    @PatchMapping("/{id}/deaktiviraj")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        departmanService.deaktiviraj(id);
        return ResponseEntity.ok().build();
    }

    // 4. Aktiviraj
    @PatchMapping("/{id}/aktiviraj")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        departmanService.aktiviraj(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/fakultet/{fakultetId}")
    public ResponseEntity<List<DepartmanDTO>> getByFakultetId(@PathVariable Long fakultetId) {
        return ResponseEntity.ok(departmanService.findByFakultetId(fakultetId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DepartmanDTO> getById(@PathVariable Long id) {
        DepartmanDTO dto = departmanService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DepartmanDTO> createDepartman(@RequestBody DepartmanCreateUpdateDTO dto) {
        return ResponseEntity.ok(departmanService.create(dto));
    }

//    @PostMapping
//    public ResponseEntity<DepartmanDTO> create(@RequestBody DepartmanDTO dto) {
//        DepartmanDTO created = departmanService.create(dto);
//        return ResponseEntity.ok(created);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DepartmanDTO> updateDepartman(@PathVariable Long id, @RequestBody DepartmanCreateUpdateDTO dto) {
        return ResponseEntity.ok(departmanService.update(id, dto));
    }
//    public ResponseEntity<DepartmanDTO> update(@PathVariable Long id, @RequestBody DepartmanDTO dto) {
//        DepartmanDTO updated = departmanService.update(id, dto);
//        if (updated == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
