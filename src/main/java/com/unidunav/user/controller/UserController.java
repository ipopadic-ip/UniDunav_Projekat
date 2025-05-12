package com.unidunav.user.controller;

import com.unidunav.profesor.model.Profesor;
import com.unidunav.profesor.repository.ProfesorRepository;

import com.unidunav.sluzbenik.model.Sluzbenik;
import com.unidunav.user.model.User;
import com.unidunav.sluzbenik.repository.SluzbenikRepository;
import com.unidunav.student.model.Student;
import com.unidunav.student.repository.StudentRepository;
import com.unidunav.user.dto.request.CreateSluzbenikRequest;
import com.unidunav.user.dto.request.CreateStudentRequest;
import com.unidunav.user.dto.request.UpdateRolesRequest;
import com.unidunav.administrator.model.Administrator;
import com.unidunav.administrator.repository.AdministratorRepository;
import com.unidunav.user.dto.request.ChangePasswordRequest;
import com.unidunav.user.dto.request.CreateAdminRequest;

import com.unidunav.user.dto.CreateUserDTO;
import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.dto.request.CreateProfessorRequest;
import com.unidunav.user.model.Role;
import com.unidunav.user.model.UserMapper;
import com.unidunav.user.repository.RoleRepository;
import com.unidunav.user.repository.UserRepository;
import com.unidunav.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    private SluzbenikRepository sluzbenikRepository;
    
    @Autowired
    private AdministratorRepository administratorRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserMapper userMapper;
    



//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public UserDTO create(@RequestBody CreateUserDTO dto) {
//        return userService.createUser(dto);
//    }

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
    
    @PostMapping("/create-profesor")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO createProfessor(@RequestBody CreateProfessorRequest request) {
        // 1. Kreiraj User entitet
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setEmail(request.getEmail());
        userDTO.setPassword(request.getPassword());

        Role role = roleRepository.findByNaziv("PROFESOR")
                .orElseThrow(() -> new RuntimeException("Rola ne postoji"));
        userDTO.setRoles(Set.of(role));

        User createdUser = userService.createUser1(userDTO);

        // 2. Kreiraj profesora i poveži ga sa korisnikom
        Profesor profesor = new Profesor();
        profesor.setIme(request.getIme());
        profesor.setPrezime(request.getPrezime());
        profesor.setBiografija(request.getBiografija());
        profesor.setUser(createdUser); // 🔥 poveži korisnika
        profesorRepository.save(profesor);

        return userMapper.toDTO(createdUser);
    }

    @PostMapping("/create-sluzbenik")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO createSluzbenik(@RequestBody CreateSluzbenikRequest request) {
        // 1. Kreiraj User entitet
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setEmail(request.getEmail());
        userDTO.setPassword(request.getPassword());

        Role role = roleRepository.findByNaziv("SLUZBENIK")
                .orElseThrow(() -> new RuntimeException("Rola ne postoji"));
        userDTO.setRoles(Set.of(role));

        User createdUser = userService.createUser1(userDTO);

        // 2. Kreiraj službenika i poveži ga sa korisnikom
        Sluzbenik sluzbenik = new Sluzbenik();
        sluzbenik.setIme(request.getIme());
        sluzbenik.setPrezime(request.getPrezime());
        sluzbenik.setUser(createdUser); // 🔥 poveži korisnika
        sluzbenikRepository.save(sluzbenik);

        return userMapper.toDTO(createdUser);
    }

    @PostMapping("/create-student")
    @PreAuthorize("hasAnyRole('SLUZBENIK', 'ADMIN')")
    public UserDTO createStudent(@RequestBody CreateStudentRequest request) {
        // 1. Kreiraj User entitet
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setEmail(request.getEmail());
        userDTO.setPassword(request.getPassword());

        Role role = roleRepository.findByNaziv("STUDENT")
                .orElseThrow(() -> new RuntimeException("Rola ne postoji"));
        userDTO.setRoles(Set.of(role));

        User createdUser = userService.createUser1(userDTO);

        // 2. Kreiraj studenta i poveži ga sa korisnikom
        Student student = new Student();
        student.setIme(request.getIme());
        student.setPrezime(request.getPrezime());
        student.setBrojIndeksa(request.getBrojIndeksa());
        student.setGodinaUpisa(request.getGodinaUpisa());
        student.setProsecnaOcena(request.getProsecnaOcena());
        student.setUkupnoEcts(request.getUkupnoEcts());
        student.setUser(createdUser); // 🔥 poveži korisnika
        studentRepository.save(student);

        return userMapper.toDTO(createdUser);
    }

    
  @PostMapping("/create-admin")
  @PreAuthorize("hasRole('ADMIN')")
  public UserDTO createAdmin(@RequestBody CreateAdminRequest request) {
      // 1. Prvo kreiraj User entitet
      CreateUserDTO userDTO = new CreateUserDTO();
      userDTO.setEmail(request.getEmail());
      userDTO.setPassword(request.getPassword());

      Role role = roleRepository.findByNaziv("ADMIN")
              .orElseThrow(() -> new RuntimeException("Rola ne postoji"));
      userDTO.setRoles(Set.of(role));

      User createdUser = userService.createUser1(userDTO); // ovo mora vratiti User sa ID-jem

      // 2. Kreiraj Administrator entitet i poveži sa user-om
      Administrator admin = new Administrator();
      admin.setIme(request.getIme());
      admin.setPrezime(request.getPrezime());
      admin.setUser(createdUser); // 🔥 obavezno

      administratorRepository.save(admin);

      // 3. Vrati DTO
      return userMapper.toDTO(createdUser);
  }
    
    @PutMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUserRoles(
            @PathVariable Long id,
            @RequestBody UpdateRolesRequest request) {

        User user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        // Očisti sve postojeće uloge korisnika (VAŽNO)
        user.getRoles().clear();

        // Učitaj nove uloge iz baze
        Set<Role> updatedRoles = request.getRoles().stream()
                .map(name -> roleRepository.findByNaziv(name)
                        .orElseThrow(() -> new RuntimeException("Rola ne postoji: " + name)))
                .collect(Collectors.toSet());

        // Postavi nove uloge
        user.getRoles().addAll(updatedRoles);

        // Sačuvaj korisnika sa novim rolama
        userService.save(user);

        return ResponseEntity.ok(userMapper.toDTO(user));
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






}
