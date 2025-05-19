package glavniPaket.service.studijskiProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.studijskiProgram.StudijskiProgramDTO;
import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.model.studijskiProgram.StudijskiProgram;
import glavniPaket.repository.godinaStudija.GodinaStudijaRepository;
import glavniPaket.repository.korisnika.ProfesorRepository;
import glavniPaket.repository.studijskiProgram.StudijskiProgramRepository;
import glavniPaket.repository.tipStudija.TipStudijaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StudijskiProgramService {

    private final StudijskiProgramRepository studijskiProgramRepository;
    private final TipStudijaRepository tipStudijaRepository;
    private final ProfesorRepository profesorRepository;
    private final GodinaStudijaRepository godinaStudijaRepository;

    @Autowired
    public StudijskiProgramService(StudijskiProgramRepository studijskiProgramRepository,
                                    TipStudijaRepository tipStudijaRepository,
                                    ProfesorRepository profesorRepository,
                                    GodinaStudijaRepository godinaStudijaRepository) {
        this.studijskiProgramRepository = studijskiProgramRepository;
        this.tipStudijaRepository = tipStudijaRepository;
        this.profesorRepository = profesorRepository;
        this.godinaStudijaRepository = godinaStudijaRepository;
    }

    // === CRUD ===

    public List<StudijskiProgramDTO> findAll() {
        return studijskiProgramRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StudijskiProgramDTO findById(Long id) {
        StudijskiProgram sp = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Studijski program sa ID " + id + " nije pronađen."));
        return mapToDTO(sp);
    }

    public StudijskiProgramDTO save(StudijskiProgramDTO dto) {
        StudijskiProgram sp = studijskiProgramRepository.save(mapToEntity(dto));
        return mapToDTO(sp);
    }

    public StudijskiProgramDTO update(Long id, StudijskiProgramDTO dto) {
        StudijskiProgram existing = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Studijski program sa ID " + id + " nije pronađen."));
        StudijskiProgram updated = mapToEntity(dto);
        updated.setId(existing.getId());
        return mapToDTO(studijskiProgramRepository.save(updated));
    }

    public void delete(Long id) {
        if (!studijskiProgramRepository.existsById(id)) {
            throw new RuntimeException("Studijski program sa ID " + id + " ne postoji.");
        }
        studijskiProgramRepository.deleteById(id);
    }

    // === DODATNE METODE ===

    public StudijskiProgramDTO findByNaziv(String naziv) {
        return mapToDTO(studijskiProgramRepository.findByNaziv(naziv)
                .orElseThrow(() -> new RuntimeException("Studijski program sa nazivom '" + naziv + "' nije pronađen.")));
    }

    public List<StudijskiProgramDTO> findByNazivStartingWith(String prefix) {
        return studijskiProgramRepository.findByNazivStartingWith(prefix).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<StudijskiProgramDTO> pretraziPoNazivu(String keyword) {
        return studijskiProgramRepository.pretraziPoNazivu(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // === MAPIRANJE ===

    private StudijskiProgram mapToEntity(StudijskiProgramDTO dto) {
        StudijskiProgram sp = new StudijskiProgram();
        sp.setId(dto.getId());
        sp.setNaziv(dto.getNaziv());
        sp.setOpis(dto.getOpis());

        if (dto.getTipStudijaId() != null) {
            sp.setTipStudija(tipStudijaRepository.findById(dto.getTipStudijaId())
                    .orElseThrow(() -> new RuntimeException("Tip studija sa ID " + dto.getTipStudijaId() + " nije pronađen.")));
        }

        if (dto.getRukovodilacId() != null) {
            sp.setRukovodilac(profesorRepository.findById(dto.getRukovodilacId())
                    .orElseThrow(() -> new RuntimeException("Profesor sa ID " + dto.getRukovodilacId() + " nije pronađen.")));
        }

        if (dto.getGodineStudijaIds() != null) {
            List<GodinaStudija> godine = dto.getGodineStudijaIds().stream()
                    .map(id -> godinaStudijaRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Godina studija sa ID " + id + " nije pronađena.")))
                    .collect(Collectors.toList());
            sp.setGodineStudija(godine);
        }

        return sp;
    }

    private StudijskiProgramDTO mapToDTO(StudijskiProgram sp) {
        List<Long> godinaIds = sp.getGodineStudija() != null
                ? sp.getGodineStudija().stream().map(GodinaStudija::getId).collect(Collectors.toList())
                : new ArrayList<>();

        return new StudijskiProgramDTO(
                sp.getId(),
                sp.getNaziv(),
                sp.getOpis(),
                sp.getTipStudija() != null ? sp.getTipStudija().getId() : null,
                sp.getRukovodilac() != null ? sp.getRukovodilac().getId() : null,
                godinaIds
        );
    }
}
