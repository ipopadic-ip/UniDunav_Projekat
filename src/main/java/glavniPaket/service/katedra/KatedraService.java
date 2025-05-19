package glavniPaket.service.katedra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.predmet.Predmet;
import glavniPaket.model.tipStudija.TipStudija;
import glavniPaket.repository.departman.DepartmanRepository;
import glavniPaket.repository.fakultet.FakultetRepository;
import glavniPaket.repository.katedra.KatedraRepository;
import glavniPaket.repository.korisnika.ProfesorRepository;
import glavniPaket.repository.predmet.PredmetRepository;
import glavniPaket.repository.tipStudija.TipStudijaRepository;

@Service
public class KatedraService {

    private final KatedraRepository katedraRepository;
    private final DepartmanRepository departmanRepository;
    private final ProfesorRepository profesorRepository;
    private final PredmetRepository predmetRepository;
    private final TipStudijaRepository tipStudijaRepository;

    @Autowired
    public KatedraService(KatedraRepository katedraRepository,
                          DepartmanRepository departmanRepository,
                          ProfesorRepository profesorRepository,
                          PredmetRepository predmetRepository,
                          TipStudijaRepository tipStudijaRepository) {
        this.katedraRepository = katedraRepository;
        this.departmanRepository = departmanRepository;
        this.profesorRepository = profesorRepository;
        this.predmetRepository = predmetRepository;
        this.tipStudijaRepository = tipStudijaRepository;
    }

    public List<KatedraDTO> findAll() {
        return katedraRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public KatedraDTO findById(Long id) {
        Katedra k = katedraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Katedra sa ID " + id + " nije pronađena."));
        return mapToDTO(k);
    }

    public KatedraDTO save(KatedraDTO dto) {
        Katedra katedra = mapToEntity(dto);
        return mapToDTO(katedraRepository.save(katedra));
    }

    public KatedraDTO update(Long id, KatedraDTO dto) {
        Katedra existing = katedraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Katedra sa ID " + id + " nije pronađena."));
        Katedra updated = mapToEntity(dto);
        updated.setId(existing.getId());
        return mapToDTO(katedraRepository.save(updated));
    }

    public void delete(Long id) {
        if (!katedraRepository.existsById(id)) {
            throw new RuntimeException("Katedra sa ID " + id + " ne postoji.");
        }
        katedraRepository.deleteById(id);
    }

    public KatedraDTO findByNaziv(String naziv) {
        Katedra k = katedraRepository.findByNaziv(naziv)
                .orElseThrow(() -> new RuntimeException("Katedra sa nazivom '" + naziv + "' nije pronađena."));
        return mapToDTO(k);
    }

    public List<KatedraDTO> findByNazivStartingWith(String prefix) {
        return katedraRepository.findByNazivStartingWith(prefix).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<KatedraDTO> pretraziPoNazivu(String keyword) {
        return katedraRepository.pretraziPoNazivu(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // === MAPIRANJE DTO -> ENTITET ===
    private Katedra mapToEntity(KatedraDTO dto) {
        Katedra k = new Katedra();
        k.setId(dto.getId());
        k.setNaziv(dto.getNaziv());
        k.setOpis(dto.getOpis());

        if (dto.getDepartmanId() != null) {
            k.setDepartman(departmanRepository.findById(dto.getDepartmanId())
                    .orElseThrow(() -> new RuntimeException("Departman sa ID " + dto.getDepartmanId() + " nije pronađen.")));
        }

        if (dto.getSefKatedreId() != null) {
            k.setSefKatedre(profesorRepository.findById(dto.getSefKatedreId())
                    .orElseThrow(() -> new RuntimeException("Profesor sa ID " + dto.getSefKatedreId() + " nije pronađen.")));
        }

        if (dto.getPredmetIds() != null) {
            List<Predmet> predmeti = dto.getPredmetIds().stream()
                    .map(id -> predmetRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Predmet sa ID " + id + " nije pronađen.")))
                    .collect(Collectors.toList());
            k.setPredmeti(new ArrayList<>(predmeti));
        }

        if (dto.getTipoviStudijaIds() != null) {
            List<TipStudija> tipovi = dto.getTipoviStudijaIds().stream()
                    .map(id -> tipStudijaRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Tip studija sa ID " + id + " nije pronađen.")))
                    .collect(Collectors.toList());
            k.setTipoviStudija(new ArrayList<>(tipovi));
        }

        return k;
    }

    // === MAPIRANJE ENTITET -> DTO ===
    private KatedraDTO mapToDTO(Katedra k) {
        List<Long> predmetIds = k.getPredmeti() != null
                ? k.getPredmeti().stream().map(Predmet::getId).collect(Collectors.toList())
                : new ArrayList<>();

        List<Long> tipoviIds = k.getTipoviStudija() != null
                ? k.getTipoviStudija().stream().map(TipStudija::getId).collect(Collectors.toList())
                : new ArrayList<>();

        return new KatedraDTO(
                k.getId(),
                k.getNaziv(),
                k.getOpis(),
                k.getDepartman() != null ? k.getDepartman().getId() : null,
                k.getSefKatedre() != null ? k.getSefKatedre().getId() : null,
                predmetIds,
                tipoviIds
        );
    }
}