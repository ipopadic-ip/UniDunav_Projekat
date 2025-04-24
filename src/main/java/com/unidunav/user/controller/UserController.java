package com.unidunav.user.controller;

import com.unidunav.profesor.model.Profesor;
import com.unidunav.profesor.repository.ProfesorRepository;

import com.unidunav.sluzbenik.model.Sluzbenik;
import com.unidunav.sluzbenik.repository.SluzbenikRepository;
import com.unidunav.student.model.Student;
import com.unidunav.student.repository.StudentRepository;
import com.unidunav.user.dto.request.CreateSluzbenikRequest;
import com.unidunav.user.dto.request.CreateStudentRequest;
import com.unidunav.administrator.model.Administrator;
import com.unidunav.administrator.repository.AdministratorRepository;
import com.unidunav.user.dto.request.CreateAdminRequest;

import com.unidunav.user.dto.CreateUserDTO;
import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.dto.request.CreateProfessorRequest;
import com.unidunav.user.model.DomainType;
import com.unidunav.user.model.Role;
import com.unidunav.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ProfesorRepository profesorRepository;
    
    @Autowired
    private SluzbenikRepository sluzbenikRepository;
    
    @Autowired
    private AdministratorRepository administratorRepository;
    
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public UserDTO create(@RequestBody CreateUserDTO dto) {
        return userService.createUser(dto);
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }
    @GetMapping("/by-email")
    public ResponseEntity<UserDTO> getByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/create-professor")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO createProfessor(@RequestBody CreateProfessorRequest request) {
        // 1. Napravi profesora u ProfesorServisu
        Profesor profesor = new Profesor();
        profesor.setIme(request.getIme());
        profesor.setPrezime(request.getPrezime());
        profesor.setBiografija(request.getBiografija());
        profesor = profesorRepository.save(profesor);

        // 2. Kreiraj korisnika sa domainId, domainType i rolu
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setEmail(request.getEmail());
        userDTO.setPassword(request.getPassword());
        userDTO.setDomainType(DomainType.PROFESSOR);
        userDTO.setDomainId(profesor.getId());
        userDTO.setRoles(Set.of(Role.PROFESSOR));

        return userService.createUser(userDTO);
    }
    
    @PostMapping("/create-sluzbenik")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO createSluzbenik(@RequestBody CreateSluzbenikRequest request) {
        // 1. Napravi entitet službenika
        Sluzbenik sluzbenik = new Sluzbenik();
        sluzbenik.setIme(request.getIme());
        sluzbenik.setPrezime(request.getPrezime());
        sluzbenik = sluzbenikRepository.save(sluzbenik);

        // 2. Kreiraj korisnika sa domainId, domainType i rolu
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setEmail(request.getEmail());
        userDTO.setPassword(request.getPassword());
        userDTO.setDomainType(DomainType.SLUZBENIK);
        userDTO.setDomainId(sluzbenik.getId());
        userDTO.setRoles(Set.of(Role.SLUZBENIK));

        return userService.createUser(userDTO);
    }
    
    @PostMapping("/create-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO createAdmin(@RequestBody CreateAdminRequest request) {
        // 1. Sačuvaj Administrator entitet
        Administrator admin = new Administrator();
        admin.setIme(request.getIme());
        admin.setPrezime(request.getPrezime());
        admin = administratorRepository.save(admin);

        // 2. Kreiraj User entitet
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setEmail(request.getEmail());
        userDTO.setPassword(request.getPassword());
        userDTO.setDomainType(DomainType.ADMIN);
        userDTO.setDomainId(admin.getId());
        userDTO.setRoles(Set.of(Role.ADMIN));

        return userService.createUser(userDTO);
    }
    
    @PostMapping("/create-student")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public UserDTO createStudent(@RequestBody CreateStudentRequest request) {
        // 1. Kreiraj entitet studenta
        Student student = new Student();
        student.setIme(request.getIme());
        student.setPrezime(request.getPrezime());
        student.setBrojIndeksa(request.getBrojIndeksa());
        student.setGodinaUpisa(request.getGodinaUpisa());
        student.setProsecnaOcena(0.0);
        student.setUkupnoEcts(0);
        student = studentRepository.save(student);

        // 2. Kreiraj User entitet
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setEmail(request.getEmail());
        userDTO.setPassword(request.getPassword());
        userDTO.setDomainType(DomainType.STUDENT);
        userDTO.setDomainId(student.getId());
        userDTO.setRoles(Set.of(Role.STUDENT));

        return userService.createUser(userDTO);
    }


}
