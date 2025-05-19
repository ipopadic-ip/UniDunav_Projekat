package glavniPaket.controller.univerzitet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.univerzitet.UniverzitetDTO;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.service.adresa.AdresaService;
import glavniPaket.service.korisnika.ProfesorService;
import glavniPaket.service.univerzitet.UniverzitetService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/univerziteti")
public class UniverzitetController {

    private final UniverzitetService univerzitetService;
    private final AdresaService adresaService;
    private final ProfesorService profesorService;

    @Autowired
    public UniverzitetController(UniverzitetService univerzitetService,
                                 AdresaService adresaService,
                                 ProfesorService profesorService) {
        this.univerzitetService = univerzitetService;
        this.adresaService = adresaService;
        this.profesorService = profesorService;
    }

    // === GET ALL ===
    @GetMapping
    public ResponseEntity<List<UniverzitetDTO>> getAll() {
        List<Univerzitet> univerziteti = univerzitetService.findAll();
        List<UniverzitetDTO> dtoList = univerziteti.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    // === GET BY ID ===
    @GetMapping("/{id}")
    public ResponseEntity<UniverzitetDTO> getById(@PathVariable Long id) {
        Univerzitet u = univerzitetService.findById(id);
        return ResponseEntity.ok(mapToDTO(u));
    }

    // === CREATE ===
    @PostMapping
    public ResponseEntity<UniverzitetDTO> create(@RequestBody UniverzitetDTO dto) {
        Univerzitet univerzitet = univerzitetService.save(mapToEntity(dto));
        return new ResponseEntity<>(mapToDTO(univerzitet), HttpStatus.CREATED);
    }

    // === UPDATE ===
    @PutMapping("/{id}")
    public ResponseEntity<UniverzitetDTO> update(@PathVariable Long id, @RequestBody UniverzitetDTO dto) {
        Univerzitet univerzitet = univerzitetService.update(id, mapToEntity(dto));
        return ResponseEntity.ok(mapToDTO(univerzitet));
    }

    // === DELETE ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        univerzitetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === DTO → ENTITY ===
    private Univerzitet mapToEntity(UniverzitetDTO dto) {
        Adresa adresa = dto.getAdresaId() != null ? adresaService.findById(dto.getAdresaId()) : null;
        Profesor rektor = dto.getRektorId() != null ? profesorService.findById(dto.getRektorId()) : null;

        Univerzitet u = new Univerzitet();
        u.setId(dto.getId());
        u.setNaziv(dto.getNaziv());
        u.setEmail(dto.getEmail());
        u.setBrojTelefona(dto.getBrojTelefona());
        u.setOpis(dto.getOpis());
        u.setAdresa(adresa);
        u.setRektor(rektor);

        return u;
    }

    // === ENTITY → DTO ===
    private UniverzitetDTO mapToDTO(Univerzitet u) {
        Long adresaId = u.getAdresa() != null ? u.getAdresa().getId() : null;
        Long rektorId = u.getRektor() != null ? u.getRektor().getId() : null;
        List<Long> fakultetIds = u.getFakulteti() != null
                ? u.getFakulteti().stream()
                    .map(Fakultet::getId)
                    .collect(Collectors.toList())
                : null;

        return new UniverzitetDTO(
                u.getId(),
                u.getNaziv(),
                u.getEmail(),
                u.getBrojTelefona(),
                u.getOpis(),
                adresaId,
                rektorId,
                fakultetIds
        );
    }
}