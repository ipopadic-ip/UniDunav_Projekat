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

    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> result = new ArrayList<>();
        for (Student s : studentService.findAll()) {
            result.add(StudentDTO.fromEntity(s));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getOne(@PathVariable Long id) { 
        return studentService.findById(id)
                .map(student -> ResponseEntity.ok(StudentDTO.fromEntity(student)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { 
        return studentService.findById(id)
                .map(s -> {
                    studentService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO dto) { 
        return studentService.findById(id)
                .map(student -> {
                    RegistrovaniKorisnik k = student.getKorisnik();
                    k.setIme(dto.getIme());
                    k.setPrezime(dto.getPrezime());
                    k.setKorisnickoIme(dto.getKorisnickoIme());
                    k.setDatumRodjenja(dto.getDatumRodjenja());
                    if (dto.getMestoRodjenja() != null)
                        k.setMestoRodjenja(new Mesto(dto.getMestoRodjenja().getId(), dto.getMestoRodjenja().getNaziv(), null));
                    k.setJmbg(dto.getJmbg());
                    k.setEmail(dto.getEmail());

                    student.setGodinaUpisa(dto.getGodinaUpisa());
                    student.setBrojIndeksa(dto.getBrojIndeksa());

                    if (dto.getAdresa() != null) {
                        student.setAdresa(new Adresa(
                                dto.getAdresa().getId(),
                                dto.getAdresa().getUlica(),
                                dto.getAdresa().getBroj(),
                                dto.getAdresa().getMesto() != null
                                        ? new Mesto(dto.getAdresa().getMesto().getId(), dto.getAdresa().getMesto().getNaziv(), null)
                                        : null
                        ));
                    } else {
                        student.setAdresa(null);
                    }

                    studentService.save(student);
                    return ResponseEntity.ok(StudentDTO.fromEntity(student));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto) {
        Student student = dto.toStudentEntity();
        student = studentService.save(student);
        return new ResponseEntity<>(StudentDTO.fromEntity(student), HttpStatus.CREATED);
    }
}
