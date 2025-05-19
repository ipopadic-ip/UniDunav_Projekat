package glavniPaket.service.korisnika;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.korisnika.StudentDTO;
import glavniPaket.model.korisnika.Student;
import glavniPaket.model.predmet.PohadjanjePredmeta;
import glavniPaket.repository.adresa.AdresaRepository;
import glavniPaket.repository.korisnika.RegistrovaniKorisnikRepository;
import glavniPaket.repository.korisnika.StudentRepository;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RegistrovaniKorisnikRepository korisnikRepository;
    private final AdresaRepository adresaRepository;
    private final PohadjanjePredmetaRepository pohadjanjeRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          RegistrovaniKorisnikRepository korisnikRepository,
                          AdresaRepository adresaRepository,
                          PohadjanjePredmetaRepository pohadjanjeRepository) {
        this.studentRepository = studentRepository;
        this.korisnikRepository = korisnikRepository;
        this.adresaRepository = adresaRepository;
        this.pohadjanjeRepository = pohadjanjeRepository;
    }

    // === CRUD ===

    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student nije pronađen sa ID: " + id));
        return mapToDTO(student);
    }

    public StudentDTO save(StudentDTO dto) {
        Student student = studentRepository.save(mapToEntity(dto));
        return mapToDTO(student);
    }

    public StudentDTO update(Long id, StudentDTO dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student sa ID " + id + " ne postoji."));
        Student updated = mapToEntity(dto);
        updated.setId(existing.getId());
        return mapToDTO(studentRepository.save(updated));
    }

    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student sa ID " + id + " ne postoji.");
        }
        studentRepository.deleteById(id);
    }
    
    
    public Optional<StudentDTO> findByKorisnikId(Long korisnikId) {
        return studentRepository.findByKorisnikId(korisnikId).map(this::mapToDTO);
    }

    public Optional<StudentDTO> findByBrojIndeksa(String brojIndeksa) {
        return studentRepository.findByBrojIndeksa(brojIndeksa).map(this::mapToDTO);
    }

    // === MAPIRANJE ===

    private StudentDTO mapToDTO(Student s) {
        List<Long> pohadjanjaIds = s.getPohadjanja() != null
                ? s.getPohadjanja().stream().map(PohadjanjePredmeta::getId).collect(Collectors.toList())
                : new ArrayList<>();

        return new StudentDTO(
                s.getId(),
                s.getKorisnik() != null ? s.getKorisnik().getId() : null,
                s.getGodinaUpisa(),
                s.getAdresa() != null ? s.getAdresa().getId() : null,
                s.getBrojIndeksa(),
                s.getUkupanBrojECTS(),
                pohadjanjaIds
        );
    }

    private Student mapToEntity(StudentDTO dto) {
        Student s = new Student();
        s.setId(dto.getId());
        s.setGodinaUpisa(dto.getGodinaUpisa());
        s.setBrojIndeksa(dto.getBrojIndeksa());
        s.setUkupanBrojECTS(dto.getUkupanBrojECTS());

        if (dto.getKorisnikId() != null) {
            s.setKorisnik(korisnikRepository.findById(dto.getKorisnikId())
                    .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen.")));
        }

        if (dto.getAdresaId() != null) {
            s.setAdresa(adresaRepository.findById(dto.getAdresaId())
                    .orElseThrow(() -> new RuntimeException("Adresa nije pronađena.")));
        }

        if (dto.getPohadjanjeIds() != null) {
            List<PohadjanjePredmeta> pohadjanja = dto.getPohadjanjeIds().stream()
                    .map(id -> pohadjanjeRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Pohadjanje predmeta sa ID " + id + " nije pronađeno.")))
                    .collect(Collectors.toList());
            s.setPohadjanja(pohadjanja);
        }

        return s;
    }
}
