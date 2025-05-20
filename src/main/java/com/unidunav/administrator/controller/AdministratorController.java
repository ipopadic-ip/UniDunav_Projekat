package com.unidunav.administrator.controller;

import com.unidunav.administrator.service.AdministratorService;
import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.dto.request.UpdateRolesRequest;
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
    
    @PutMapping("/{id}/roles")
    public ResponseEntity<UserDTO> updateUserRoles(
            @PathVariable Long id,
            @RequestBody UpdateRolesRequest request) {

        UserDTO updatedUser = service.updateUserRoles(id, request);
        return ResponseEntity.ok(updatedUser);
    }


    @PutMapping("/users/{userId}/add-role")
    public ResponseEntity<UserDTO> addRole(@PathVariable Long userId, @RequestParam String roleName) {
        return ResponseEntity.ok(service.addRoleToUser(userId, roleName));
    }

    @PutMapping("/users/{userId}/remove-role")
    public ResponseEntity<UserDTO> removeRole(@PathVariable Long userId, @RequestParam String roleName) {
        return ResponseEntity.ok(service.removeRoleFromUser(userId, roleName));
    }

    // Ovde možeš dodavati ostale admin-specifične operacije
}
