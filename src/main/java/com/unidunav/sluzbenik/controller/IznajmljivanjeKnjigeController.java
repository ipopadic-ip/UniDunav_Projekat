package com.unidunav.sluzbenik.controller;

import com.unidunav.sluzbenik.dto.IznajmljivanjeKnjigeDTO;
import com.unidunav.sluzbenik.model.IznajmljivanjeKnjige;
import com.unidunav.sluzbenik.service.IznajmljivanjeKnjigeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/iznajmljivanja")
public class IznajmljivanjeKnjigeController {

    private final IznajmljivanjeKnjigeService iznajmljivanjeService;

    public IznajmljivanjeKnjigeController(IznajmljivanjeKnjigeService iznajmljivanjeService) {
        this.iznajmljivanjeService = iznajmljivanjeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<IznajmljivanjeKnjige> iznajmi(@RequestBody IznajmljivanjeKnjigeDTO dto) {
        IznajmljivanjeKnjige novo = iznajmljivanjeService.iznajmiKnjigu(dto);
        return new ResponseEntity<>(novo, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<IznajmljivanjeKnjige>> svaIznajmljivanja() {
        return ResponseEntity.ok(iznajmljivanjeService.svaIznajmljivanja());
    }
    
    @PutMapping("/{id}/vrati")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<Void> vratiKnjigu(@PathVariable Long id) {
        iznajmljivanjeService.vratiKnjigu(id);
        return ResponseEntity.noContent().build();
    }

}
