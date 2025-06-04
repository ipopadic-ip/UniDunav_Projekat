package com.unidunav.obavestenje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.obavestenje.dto.OpsteObavestenjeDTO;
import com.unidunav.obavestenje.dto.OpsteObavestenjeResponseDTO;
import com.unidunav.obavestenje.model.OpsteObavestenje;
import com.unidunav.obavestenje.service.OpsteObavestenjeService;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/opsta-obavestenja")
@CrossOrigin(origins = "*") // ili preciznije ako znaš frontend URL
public class OpsteObavestenjeController {

    @Autowired
    private OpsteObavestenjeService service;
    
    @Autowired
    private UserRepository korisnikRepo;
    

    @GetMapping
    public List<OpsteObavestenjeResponseDTO> getAll() {
        return service.findAll().stream()
            .map(OpsteObavestenjeResponseDTO::new)
            .toList();
    }

    @GetMapping("/{id}")
    public OpsteObavestenjeResponseDTO getOne(@PathVariable Long id) {
        OpsteObavestenje o = service.findById(id);
        return new OpsteObavestenjeResponseDTO(o);
    }
    @PostMapping
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<?> create(@RequestBody OpsteObavestenjeDTO dto, Principal principal) {
        User autor = korisnikRepo.findByEmail(principal.getName())
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        OpsteObavestenje obavestenje = service.create(dto, autor);

        OpsteObavestenjeResponseDTO responseDTO = new OpsteObavestenjeResponseDTO(obavestenje);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    

    @PutMapping("/{id}")
    public OpsteObavestenje update(@PathVariable Long id, @RequestBody OpsteObavestenje obavestenje) {
        obavestenje.setId(id);
        return service.save(obavestenje);
    }
}
