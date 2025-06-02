package com.unidunav.user.controller;

import com.unidunav.user.dto.RoleDTO;
import com.unidunav.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Dohvatanje svih aktivnih rola
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleDTO> getAllActiveRoles() {
        return roleService.getAllActiveRoles();
    }
    
 // Dohvatanje svih rola, aktivne prve
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleDTO> getAllRolesSorted() {
        return roleService.getAllRolesSorted();
    }

    // Ažuriranje role (naziv ili aktivnost)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RoleDTO updateRole(@PathVariable Long id, @RequestBody RoleDTO updatedRoleDTO) {
        return roleService.updateRole(id, updatedRoleDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RoleDTO getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }



    // Dodavanje nove role
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }
    
    @PutMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public void activateRole(@PathVariable Long id) {
        roleService.activateRole(id);
    }


    // Logičko brisanje role
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deactivateRole(@PathVariable Long id) {
        roleService.deactivateRole(id);
    }
}