package com.unidunav.administrator.controller;

import com.unidunav.administrator.dto.AdministratorDTO;
import com.unidunav.administrator.service.AdministratorService;
import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN')")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @PostMapping
    public AdministratorDTO create(@RequestBody AdministratorDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<AdministratorDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AdministratorDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public AdministratorDTO update(@PathVariable Long id, @RequestBody AdministratorDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
//    http: http://localhost:8080/api/admin/users/8/add-role?role=ADMIN
    @PutMapping("/users/{userId}/add-role")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> addRole(@PathVariable Long userId, @RequestParam Role role) {
        return ResponseEntity.ok(service.addRoleToUser(userId, role));
    }

//    htttp: http://localhost:8080/api/admin/users/8/remove-role?role=ADMIN
    @PutMapping("/users/{userId}/remove-role")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> removeRole(@PathVariable Long userId, @RequestParam Role role) {
        return ResponseEntity.ok(service.removeRoleFromUser(userId, role));
    }
}