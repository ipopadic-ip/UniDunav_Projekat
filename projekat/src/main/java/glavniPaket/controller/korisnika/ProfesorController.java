package glavniPaket.controller.korisnika;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import glavniPaket.dto.korisnika.ProfesorDTO;

import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.korisnika.*;
import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.service.korisnika.ProfesorService;

@RestController
@RequestMapping("/api/profesori")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> getAll() {
        List<ProfesorDTO> result = new ArrayList<>();
        for (Profesor p : profesorService.findAll()) {
            result.add(ProfesorDTO.fromEntity(p));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> getOne(@PathVariable Integer id) {
        return profesorService.findById(id)
                .map(profesor -> ResponseEntity.ok(ProfesorDTO.fromEntity(profesor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return profesorService.findById(id)
                .map(p -> {
                    profesorService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ProfesorDTO> update(@PathVariable Integer id, @RequestBody ProfesorDTO dto) {
//        return profesorService.findById(id)
//                .map(profesor -> {
//                    profesor.getKorisnik().setIme(dto.getIme());
//                    profesor.getKorisnik().setPrezime(dto.getPrezime());
//                    profesor.getKorisnik().setKorisnickoIme(dto.getKorisnickoIme());
//                    profesor.getKorisnik().setDatumRodjenja(dto.getDatumRodjenja());
//                    profesor.getKorisnik().setMestoRodjenja(dto.getMestoRodjenja().toEntity());
//                    profesor.getKorisnik().setJmbg(dto.getJmbg());
//                    profesor.getKorisnik().setEmail(dto.getEmail());
//
//                    profesor.setTitula(dto.getTitula());
//                    profesor.setBiografija(dto.getBiografija());
//
//                    if (dto.getUniverzitetId() != null) profesor.setUniverzitet(new model.univerzitet.Univerzitet(dto.getUniverzitetId()));
//                    if (dto.getFakultetId() != null) profesor.setFakultet(new model.fakultet.Fakultet(dto.getFakultetId()));
//                    if (dto.getKatedraId() != null) profesor.setKatedra(new model.katedra.Katedra(dto.getKatedraId()));
//
//                    profesorService.save(profesor);
//                    return ResponseEntity.ok(ProfesorDTO.fromEntity(profesor));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> create(@RequestBody ProfesorDTO dto) {
        Profesor profesor = dto.toProfesorEntity();
        profesor = profesorService.save(profesor);
        return new ResponseEntity<>(ProfesorDTO.fromEntity(profesor), HttpStatus.CREATED);
    }
}
