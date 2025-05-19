package glavniPaket.service.fakultet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import glavniPaket.dto.fakultet.FakultetDTO;
import glavniPaket.model.departman.Departman;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.repository.departman.DepartmanRepository;
import glavniPaket.repository.fakultet.FakultetRepository;
import glavniPaket.repository.korisnika.ProfesorRepository;
import glavniPaket.repository.univerzitet.UniverzitetRepository;

@Service
public class FakultetService {

    private final FakultetRepository fakultetRepository;
    private final UniverzitetRepository univerzitetRepository;
    private final ProfesorRepository profesorRepository;
    private final DepartmanRepository departmanRepository;

    @Autowired
    public FakultetService(FakultetRepository fakultetRepository,
                           UniverzitetRepository univerzitetRepository,
                           ProfesorRepository profesorRepository,
                           DepartmanRepository departmanRepository) {
        this.fakultetRepository = fakultetRepository;
        this.univerzitetRepository = univerzitetRepository;
        this.profesorRepository = profesorRepository;
        this.departmanRepository = departmanRepository;
    }

    // === GET ALL ===
    public List<FakultetDTO> findAll() {
        return fakultetRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // === GET BY ID ===
    public FakultetDTO findById(Long id) {
        Fakultet f = fakultetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fakultet sa ID " + id + " nije pronađen."));
        return mapToDTO(f);
    }

    // === CREATE ===
    public FakultetDTO save(FakultetDTO dto) {
        Fakultet entity = mapToEntity(dto);
        return mapToDTO(fakultetRepository.save(entity));
    }

    // === UPDATE ===
    public FakultetDTO update(Long id, FakultetDTO dto) {
        Fakultet existing = fakultetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fakultet sa ID " + id + " nije pronađen."));

        Fakultet updated = mapToEntity(dto);
        updated.setId(existing.getId());

        return mapToDTO(fakultetRepository.save(updated));
    }

    // === DELETE ===
    public void delete(Long id) {
        if (!fakultetRepository.existsById(id)) {
            throw new RuntimeException("Fakultet sa ID " + id + " ne postoji.");
        }
        fakultetRepository.deleteById(id);
    }

    // === DODATNE METODE ===

    public FakultetDTO findByNaziv(String naziv) {
        Fakultet f = fakultetRepository.findByNaziv(naziv)
                .orElseThrow(() -> new RuntimeException("Fakultet sa nazivom '" + naziv + "' nije pronađen."));
        return mapToDTO(f);
    }

    public List<FakultetDTO> findByNazivStartingWith(String prefix) {
        return fakultetRepository.findByNazivStartingWith(prefix).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public boolean existsByEmail(String email) {
        return fakultetRepository.existsByEmail(email);
    }

    public List<FakultetDTO> pretraziPoNazivu(String keyword) {
        return fakultetRepository.pretraziPoNazivu(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // === MAPIRANJE ENTITET <-> DTO ===

    private FakultetDTO mapToDTO(Fakultet f) {
        List<Long> departmanIds = f.getDepartmani() != null
                ? f.getDepartmani().stream().map(Departman::getId).collect(Collectors.toList())
                : new ArrayList<>();

        return new FakultetDTO(
                f.getId(),
                f.getNaziv(),
                f.getEmail(),
                f.getOpis(),
                f.getUniverzitet() != null ? f.getUniverzitet().getId() : null,
                f.getDekan() != null ? f.getDekan().getId() : null,
                departmanIds
        );
    }

    private Fakultet mapToEntity(FakultetDTO dto) {
        Fakultet f = new Fakultet();

        f.setId(dto.getId());
        f.setNaziv(dto.getNaziv());
        f.setEmail(dto.getEmail());
        f.setOpis(dto.getOpis());

        if (dto.getUniverzitetId() != null) {
            f.setUniverzitet(univerzitetRepository.findById(dto.getUniverzitetId())
                    .orElseThrow(() -> new RuntimeException("Univerzitet sa ID " + dto.getUniverzitetId() + " nije pronađen.")));
        }

        if (dto.getDekanId() != null) {
            f.setDekan(profesorRepository.findById(dto.getDekanId())
                    .orElseThrow(() -> new RuntimeException("Dekan sa ID " + dto.getDekanId() + " nije pronađen.")));
        }

        if (dto.getDepartmanIds() != null) {
            List<Departman> departmani = dto.getDepartmanIds().stream()
                    .map(id -> departmanRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Departman sa ID " + id + " nije pronađen.")))
                    .collect(Collectors.toList());
            f.setDepartmani(new ArrayList<>(departmani));
        }

        return f;
    }
}