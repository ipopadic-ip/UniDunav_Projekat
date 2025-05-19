package glavniPaket.controller.korisnika;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import glavniPaket.dto.korisnika.StudentDTO;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;
import glavniPaket.model.korisnika.Student;
import glavniPaket.service.korisnika.StudentService;


@RestController
@RequestMapping("/api/studenti")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === Dodatne opcione rute ===

    @GetMapping("/korisnik/{korisnikId}")
    public ResponseEntity<StudentDTO> getByKorisnikId(@PathVariable Long korisnikId) {
        return studentService.findByKorisnikId(korisnikId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/indeks/{brojIndeksa}")
    public ResponseEntity<StudentDTO> getByBrojIndeksa(@PathVariable String brojIndeksa) {
        return studentService.findByBrojIndeksa(brojIndeksa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}