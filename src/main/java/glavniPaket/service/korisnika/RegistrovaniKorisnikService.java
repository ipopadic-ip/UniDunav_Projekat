package glavniPaket.service.korisnika;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import glavniPaket.repository.adresa.MestoRepository;
import glavniPaket.repository.korisnika.ProfesorRepository;
import glavniPaket.repository.korisnika.RegistrovaniKorisnikRepository;
import glavniPaket.repository.korisnika.StudentRepository;
import glavniPaket.dto.korisnika.RegistrovaniKorisnikDTO;
import glavniPaket.model.korisnika.*;

@Service
public class RegistrovaniKorisnikService {

    private final RegistrovaniKorisnikRepository korisnikRepository;
    private final MestoRepository mestoRepository;
    private final StudentRepository studentRepository;
    private final ProfesorRepository profesorRepository;

    @Autowired
    public RegistrovaniKorisnikService(RegistrovaniKorisnikRepository korisnikRepository,
                                       MestoRepository mestoRepository,
                                       StudentRepository studentRepository,
                                       ProfesorRepository profesorRepository) {
        this.korisnikRepository = korisnikRepository;
        this.mestoRepository = mestoRepository;
        this.studentRepository = studentRepository;
        this.profesorRepository = profesorRepository;
    }

    // === CRUD ===

    public List<RegistrovaniKorisnikDTO> findAll() {
        return korisnikRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public RegistrovaniKorisnikDTO findById(Long id) {
        RegistrovaniKorisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Korisnik sa ID " + id + " nije pronađen."));
        return mapToDTO(korisnik);
    }

    public RegistrovaniKorisnikDTO save(RegistrovaniKorisnikDTO dto) {
        RegistrovaniKorisnik korisnik = korisnikRepository.save(mapToEntity(dto));
        return mapToDTO(korisnik);
    }

    public RegistrovaniKorisnikDTO update(Long id, RegistrovaniKorisnikDTO dto) {
        RegistrovaniKorisnik existing = korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Korisnik sa ID " + id + " nije pronađen."));
        RegistrovaniKorisnik updated = mapToEntity(dto);
        updated.setId(existing.getId());
        return mapToDTO(korisnikRepository.save(updated));
    }

    public void delete(Long id) {
        if (!korisnikRepository.existsById(id)) {
            throw new RuntimeException("Korisnik sa ID " + id + " ne postoji.");
        }
        korisnikRepository.deleteById(id);
    }

    // === DODATNE METODE IZ REPOZITORIJUMA ===

    public RegistrovaniKorisnikDTO findByEmail(String email) {
        RegistrovaniKorisnik korisnik = korisnikRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Korisnik sa emailom '" + email + "' nije pronađen."));
        return mapToDTO(korisnik);
    }

    public RegistrovaniKorisnikDTO findByKorisnickoIme(String korisnickoIme) {
        RegistrovaniKorisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme)
                .orElseThrow(() -> new RuntimeException("Korisnik sa korisničkim imenom '" + korisnickoIme + "' nije pronađen."));
        return mapToDTO(korisnik);
    }

    public List<RegistrovaniKorisnikDTO> findByImeAndPrezime(String ime, String prezime) {
        return korisnikRepository.findByImeAndPrezime(ime, prezime).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<RegistrovaniKorisnikDTO> findByImeStartingWith(String prefix) {
        return korisnikRepository.findByImeStartingWith(prefix).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<RegistrovaniKorisnikDTO> pretraziPoImenuIliPrezimenu(String keyword) {
        return korisnikRepository.pretraziPoImenuIliPrezimenu(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public boolean existsByEmail(String email) {
        return korisnikRepository.existsByEmail(email);
    }

    public boolean existsByJmbg(String jmbg) {
        return korisnikRepository.existsByJmbg(jmbg);
    }

    // === MAPIRANJE ===

    private RegistrovaniKorisnikDTO mapToDTO(RegistrovaniKorisnik korisnik) {
        return new RegistrovaniKorisnikDTO(
                korisnik.getId(),
                korisnik.getIme(),
                korisnik.getPrezime(),
                korisnik.getKorisnickoIme(),
                korisnik.getDatumRodjenja(),
                korisnik.getJmbg(),
                korisnik.getLozinka(),
                korisnik.getEmail(),
                korisnik.getMestoRodjenja() != null ? korisnik.getMestoRodjenja().getId() : null,
                korisnik.getStudent() != null ? korisnik.getStudent().getId() : null,
                korisnik.getProfesor() != null ? korisnik.getProfesor().getId() : null
        );
    }

    private RegistrovaniKorisnik mapToEntity(RegistrovaniKorisnikDTO dto) {
        RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik();
        korisnik.setId(dto.getId());
        korisnik.setIme(dto.getIme());
        korisnik.setPrezime(dto.getPrezime());
        korisnik.setKorisnickoIme(dto.getKorisnickoIme());
        korisnik.setDatumRodjenja(dto.getDatumRodjenja());
        korisnik.setJmbg(dto.getJmbg());
        korisnik.setLozinka(dto.getLozinka());
        korisnik.setEmail(dto.getEmail());

        if (dto.getMestoRodjenjaId() != null) {
            korisnik.setMestoRodjenja(
                    mestoRepository.findById(dto.getMestoRodjenjaId())
                            .orElseThrow(() -> new RuntimeException("Mesto sa ID " + dto.getMestoRodjenjaId() + " nije pronađeno."))
            );
        }

        if (dto.getStudentId() != null) {
            korisnik.setStudent(
                    studentRepository.findById(dto.getStudentId())
                            .orElseThrow(() -> new RuntimeException("Student sa ID " + dto.getStudentId() + " nije pronađen."))
            );
        }

        if (dto.getProfesorId() != null) {
            korisnik.setProfesor(
                    profesorRepository.findById(dto.getProfesorId())
                            .orElseThrow(() -> new RuntimeException("Profesor sa ID " + dto.getProfesorId() + " nije pronađen."))
            );
        }

        return korisnik;
    }
}

