package glavniPaket.controller.studijskiProgram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.studijskiProgram.StudijskiProgramDTO;
import glavniPaket.model.studijskiProgram.StudijskiProgram;
import glavniPaket.service.studijskiProgram.StudijskiProgramService;

@RestController
@RequestMapping("/studijski-program")
public class StudijskiProgramController {

    @Autowired
    private StudijskiProgramService studijskiProgramService;

    public StudijskiProgramController(StudijskiProgramService studijskiProgramService) {
        this.studijskiProgramService = studijskiProgramService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudijskiProgram> getById(@PathVariable Long id) {
        return studijskiProgramService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<StudijskiProgram>> getAll() {
        Iterable<StudijskiProgram> studijskiProgrami = studijskiProgramService.findAll();
        return ResponseEntity.ok(studijskiProgrami);
    }

    @PostMapping
    public ResponseEntity<StudijskiProgram> create(@RequestBody StudijskiProgram studijskiProgram) {
        StudijskiProgram savedStudijskiProgram = studijskiProgramService.save(studijskiProgram);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudijskiProgram);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudijskiProgram> update(@PathVariable Long id, @RequestBody StudijskiProgram studijskiProgram) {
        if (studijskiProgramService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        studijskiProgram.setId(id);
        StudijskiProgram updatedStudijskiProgram = studijskiProgramService.save(studijskiProgram);
        return ResponseEntity.ok(updatedStudijskiProgram);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (studijskiProgramService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        studijskiProgramService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<StudijskiProgramDTO> add(@RequestBody StudijskiProgramDTO studijskiProgramDTO) {
        StudijskiProgram noviStudijskiProgram = new StudijskiProgram(null, studijskiProgramDTO.getNaziv(), studijskiProgramDTO.getOpis(), 
                                                                    studijskiProgramDTO.getGodinaStudija(), studijskiProgramDTO.getTipStudija(), 
                                                                    studijskiProgramDTO.getRukovodilac());
        this.studijskiProgramService.save(noviStudijskiProgram);

        return new ResponseEntity<StudijskiProgramDTO>(
            new StudijskiProgramDTO(
                noviStudijskiProgram.getId(),
                noviStudijskiProgram.getNaziv(),
                noviStudijskiProgram.getOpis(),
                noviStudijskiProgram.getGodinaStudija(),
                noviStudijskiProgram.getTipStudija(),
                noviStudijskiProgram.getRukovodilac()
            ), HttpStatus.CREATED);
    }
}
