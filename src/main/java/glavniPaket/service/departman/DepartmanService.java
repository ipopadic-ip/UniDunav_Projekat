package glavniPaket.service.departman;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.departman.DepartmanDTO;
import glavniPaket.model.departman.Departman;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.repository.departman.DepartmanRepository;
import glavniPaket.repository.fakultet.FakultetRepository;
import glavniPaket.repository.katedra.KatedraRepository;
import glavniPaket.repository.korisnika.ProfesorRepository;

@Service
public class DepartmanService {

    private final DepartmanRepository departmanRepository;
    private final FakultetRepository fakultetRepository;
    private final ProfesorRepository profesorRepository;
    private final KatedraRepository katedraRepository;

    @Autowired
    public DepartmanService(DepartmanRepository departmanRepository,
                            FakultetRepository fakultetRepository,
                            ProfesorRepository profesorRepository,
                            KatedraRepository katedraRepository) {
        this.departmanRepository = departmanRepository;
        this.fakultetRepository = fakultetRepository;
        this.profesorRepository = profesorRepository;
        this.katedraRepository = katedraRepository;
    }

    // === CRUD ===

    public List<DepartmanDTO> findAll() {
        return departmanRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public DepartmanDTO findById(Long id) {
        Departman d = departmanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departman sa ID " + id + " nije pronađen."));
        return mapToDTO(d);
    }

    public DepartmanDTO save(DepartmanDTO dto) {
        Departman entity = mapToEntity(dto);
        return mapToDTO(departmanRepository.save(entity));
    }

    public DepartmanDTO update(Long id, DepartmanDTO dto) {
        Departman existing = departmanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departman sa ID " + id + " nije pronađen."));

        Departman updated = mapToEntity(dto);
        updated.setId(existing.getId());

        return mapToDTO(departmanRepository.save(updated));
    }

    public void delete(Long id) {
        if (!departmanRepository.existsById(id)) {
            throw new RuntimeException("Departman sa ID " + id + " ne postoji.");
        }
        departmanRepository.deleteById(id);
    }

    // === DODATNE METODE ===

    public DepartmanDTO findByNaziv(String naziv) {
        Departman d = departmanRepository.findByNaziv(naziv)
                .orElseThrow(() -> new RuntimeException("Departman '" + naziv + "' nije pronađen."));
        return mapToDTO(d);
    }

    public List<DepartmanDTO> findByNazivStartingWith(String prefix) {
        return departmanRepository.findByNazivStartingWith(prefix).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public boolean existsByNaziv(String naziv) {
        return departmanRepository.existsByNaziv(naziv);
    }

    public List<DepartmanDTO> pretraziPoNazivu(String keyword) {
        return departmanRepository.pretraziPoNazivu(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // === MAPE ===

    private DepartmanDTO mapToDTO(Departman d) {
        List<Long> katedraIds = d.getKatedre() != null
                ? d.getKatedre().stream().map(Katedra::getId).collect(Collectors.toList())
                : new ArrayList<>();

        return new DepartmanDTO(
                d.getId(),
                d.getNaziv(),
                d.getOpis(),
                d.getFakultet() != null ? d.getFakultet().getId() : null,
                d.getSefDepartmana() != null ? d.getSefDepartmana().getId() : null,
                katedraIds
        );
    }

    private Departman mapToEntity(DepartmanDTO dto) {
        Departman d = new Departman();
        d.setId(dto.getId());
        d.setNaziv(dto.getNaziv());
        d.setOpis(dto.getOpis());

        if (dto.getFakultetId() != null) {
            d.setFakultet(fakultetRepository.findById(dto.getFakultetId())
                    .orElseThrow(() -> new RuntimeException("Fakultet sa ID " + dto.getFakultetId() + " nije pronađen.")));
        }

        if (dto.getSefDepartmanaId() != null) {
            d.setSefDepartmana(profesorRepository.findById(dto.getSefDepartmanaId())
                    .orElseThrow(() -> new RuntimeException("Profesor sa ID " + dto.getSefDepartmanaId() + " nije pronađen.")));
        }

        if (dto.getKatedraIds() != null) {
            List<Katedra> katedre = dto.getKatedraIds().stream()
                    .map(id -> katedraRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Katedra sa ID " + id + " nije pronađena.")))
                    .collect(Collectors.toList());
            d.setKatedre(new ArrayList<>(katedre));
        }

        return d;
    }
}
