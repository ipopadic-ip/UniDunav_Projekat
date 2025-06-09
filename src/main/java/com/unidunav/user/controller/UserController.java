package com.unidunav.user.controller;

import com.unidunav.profesor.model.Profesor;
import com.unidunav.profesor.repository.ProfesorRepository;

import com.unidunav.user.model.User;
import com.unidunav.student.model.Student;
import com.unidunav.student.repository.StudentRepository;
import com.unidunav.user.dto.request.UpdateRolesRequest;
import com.unidunav.user.dto.request.UserUpdateMeDTO;
import com.unidunav.user.dto.request.ChangePasswordRequest;

import com.unidunav.user.dto.CreateUserDTO;
import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.model.Role;
import com.unidunav.user.repository.RoleRepository;
import com.unidunav.user.repository.UserRepository;
import com.unidunav.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ProfesorRepository profesorRepository;
    
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
//    @GetMapping
//    public List<UserDTO> getAllUsers() {
//        return userService.getAllUsers();
//    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }
    
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllAdmin() {
        return userService.getAllAdmin();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SLUZBENIK')")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getMyProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getById(user.getId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateMe(@RequestBody UserUpdateMeDTO dto) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.updateMe(dto, currentUser);
        return ResponseEntity.ok("Profil uspešno ažuriran.");
    }



    @GetMapping("/by-email")
    public ResponseEntity<UserDTO> getByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'SLUZBENIK')")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO request) throws AccessDeniedException {
        userService.createUserWithRole(request);
        return ResponseEntity.ok("Korisnik uspešno kreiran.");
    }
    
    @PutMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean success = userService.changePassword(user.getId(), request.getOldPassword(), request.getNewPassword());

        if (success) {
            return ResponseEntity.ok("Lozinka uspešno promenjena.");
        } else {
            return ResponseEntity.badRequest().body("Stara lozinka nije tačna.");
        }
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) throws AccessDeniedException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean success = userService.updateUser(id, dto, currentUser);
        return success ? ResponseEntity.ok("Korisnik ažuriran.") : ResponseEntity.badRequest().build();
    }
    
    @PutMapping("/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> logicallyDeleteUser(@PathVariable Long id) throws AccessDeniedException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean success = userService.deleteUser(id, currentUser);
        return success ? ResponseEntity.ok("Korisnik je logički obrisan.") : ResponseEntity.badRequest().build();
    }
    
    @PutMapping("/{id}/restore")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> restoreUser(@PathVariable Long id) {
        boolean success = userService.restoreUser(id);
        return success ? ResponseEntity.ok("Korisnik je uspešno obnovljen.") : ResponseEntity.badRequest().body("Korisnik nije pronađen ili već nije obrisan.");
    }


}
