package glavniPaket.controller.studijskiProgram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.studijskiProgram.StudijskiProgramDTO;
import glavniPaket.model.studijskiProgram.StudijskiProgram;
import glavniPaket.service.studijskiProgram.StudijskiProgramService;

@RestController
@RequestMapping("/api/studijski-programi")
public class StudijskiProgramController {

    @Autowired
    private StudijskiProgramService studijskiProgramService;

    public StudijskiProgramController(StudijskiProgramService studijskiProgramService) {
        this.studijskiProgramService = studijskiProgramService;
    }

    // Get a studijski program by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudijskiProgramDTO> getById(@PathVariable Long id) {
        return studijskiProgramService.findById(id)
            .map(studijskiProgram -> ResponseEntity.ok(new StudijskiProgramDTO(studijskiProgram)))
            .orElse(ResponseEntity.notFound().build());
    }

    // Get all studijski programi
    @GetMapping
    public ResponseEntity<Iterable<StudijskiProgramDTO>> getAll() {
        Iterable<StudijskiProgram> studijskiProgrami = studijskiProgramService.findAll();
        Iterable<StudijskiProgramDTO> studijskiProgramDTOs = studijskiProgramService.convertToDTOs(studijskiProgrami);
        return ResponseEntity.ok(studijskiProgramDTOs);
    }

    // Create a new studijski program
    @PostMapping
    public ResponseEntity<StudijskiProgramDTO> create(@RequestBody StudijskiProgramDTO studijskiProgramDTO) {
        StudijskiProgram studijskiProgram = new StudijskiProgram(
                null, 
                studijskiProgramDTO.getNaziv(), 
                studijskiProgramDTO.getOpis(),
                studijskiProgramDTO.getGodinaStudija() != null ? studijskiProgramDTO.getGodinaStudija().toEntity() : null,
                studijskiProgramDTO.getTipStudija() != null ? studijskiProgramDTO.getTipStudija().toEntity() : null,
                studijskiProgramDTO.getRukovodilac() != null ? studijskiProgramDTO.getRukovodilac().toEntity() : null
        );

        StudijskiProgram savedStudijskiProgram = studijskiProgramService.save(studijskiProgram);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new StudijskiProgramDTO(savedStudijskiProgram));
    }

    // Update an existing studijski program
    @PutMapping("/{id}")
    public ResponseEntity<StudijskiProgramDTO> update(@PathVariable Long id, @RequestBody StudijskiProgramDTO studijskiProgramDTO) {
        if (studijskiProgramService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        StudijskiProgram studijskiProgram = new StudijskiProgram(
                id,
                studijskiProgramDTO.getNaziv(),
                studijskiProgramDTO.getOpis(),
                studijskiProgramDTO.getGodinaStudija() != null ? studijskiProgramDTO.getGodinaStudija().toEntity() : null,
                studijskiProgramDTO.getTipStudija() != null ? studijskiProgramDTO.getTipStudija().toEntity() : null,
                studijskiProgramDTO.getRukovodilac() != null ? studijskiProgramDTO.getRukovodilac().toEntity() : null
        );

        StudijskiProgram updatedStudijskiProgram = studijskiProgramService.save(studijskiProgram);
        return ResponseEntity.ok(new StudijskiProgramDTO(updatedStudijskiProgram));
    }

    // Delete a studijski program by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (studijskiProgramService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        studijskiProgramService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
