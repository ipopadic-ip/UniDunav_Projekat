package com.unidunav.administrator.controller;

import com.unidunav.administrator.service.AdministratorService;
import com.unidunav.student.repository.StudentRepository;
import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.dto.request.UpdateRolesRequest;
import com.unidunav.user.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    
    @GetMapping("/export/profesors/pdf")
    public ResponseEntity<Resource> exportProfesorsPdf() {
        try {
            Resource pdf = service.exportProfesorsToPdf();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=profesori.pdf")
                    .body(pdf);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/export/profesors/xml")
    public ResponseEntity<Resource> exportProfesorsXml() {
        try {
            Resource xml = service.exportProfesorsToXml();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=profesori.xml")
                    .body(xml);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    @GetMapping("/export/students/pdf")
    public ResponseEntity<Resource> exportStudentsPdf() {
        try {
            Resource pdf = service.exportStudentsToPdf();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=studenti.pdf")
                    .body(pdf);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/export/students/xml")
    public ResponseEntity<Resource> exportStudentsXml() {
        try {
            Resource xml = service.exportStudentsToXml();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=studenti.xml")
                    .body(xml);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
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
