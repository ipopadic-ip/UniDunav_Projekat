package glavniPaket.service.tipStudija;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.tipStudija.TipStudijaDTO;
import glavniPaket.model.studijskiProgram.StudijskiProgram;
import glavniPaket.model.tipStudija.TipStudija;
import glavniPaket.repository.katedra.KatedraRepository;
import glavniPaket.repository.studijskiProgram.StudijskiProgramRepository;
import glavniPaket.repository.tipStudija.TipStudijaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TipStudijaService {

    private final TipStudijaRepository tipStudijaRepository;
    private final KatedraRepository katedraRepository;
    private final StudijskiProgramRepository studijskiProgramRepository;

    @Autowired
    public TipStudijaService(
            TipStudijaRepository tipStudijaRepository,
            KatedraRepository katedraRepository,
            StudijskiProgramRepository studijskiProgramRepository) {
        this.tipStudijaRepository = tipStudijaRepository;
        this.katedraRepository = katedraRepository;
        this.studijskiProgramRepository = studijskiProgramRepository;
    }

    public List<TipStudijaDTO> findAll() {
        return tipStudijaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TipStudijaDTO findById(Long id) {
        TipStudija tip = tipStudijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tip studija sa ID " + id + " nije pronađen."));
        return mapToDTO(tip);
    }

    public TipStudijaDTO save(TipStudijaDTO dto) {
        TipStudija tip = tipStudijaRepository.save(mapToEntity(dto));
        return mapToDTO(tip);
    }

    public TipStudijaDTO update(Long id, TipStudijaDTO dto) {
        TipStudija existing = tipStudijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tip studija sa ID " + id + " nije pronađen."));
        TipStudija updated = mapToEntity(dto);
        updated.setId(existing.getId());
        return mapToDTO(tipStudijaRepository.save(updated));
    }

    public void delete(Long id) {
        if (!tipStudijaRepository.existsById(id)) {
            throw new RuntimeException("Tip studija sa ID " + id + " ne postoji.");
        }
        tipStudijaRepository.deleteById(id);
    }

    public TipStudijaDTO findByTip(String tip) {
        return mapToDTO(tipStudijaRepository.findByTip(tip)
                .orElseThrow(() -> new RuntimeException("Tip studija '" + tip + "' nije pronađen.")));
    }

    public List<TipStudijaDTO> findByTipStartingWith(String prefix) {
        return tipStudijaRepository.findByTipStartingWith(prefix).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<TipStudijaDTO> pretraziPoTipu(String keyword) {
        return tipStudijaRepository.pretraziPoTipu(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // === MAPIRANJE ===

    private TipStudija mapToEntity(TipStudijaDTO dto) {
        TipStudija tip = new TipStudija();
        tip.setId(dto.getId());
        tip.setTip(dto.getTip());

        if (dto.getKatedraId() != null) {
            tip.setKatedra(katedraRepository.findById(dto.getKatedraId())
                    .orElseThrow(() -> new RuntimeException("Katedra sa ID " + dto.getKatedraId() + " nije pronađena.")));
        }

        if (dto.getStudijskiProgramiIds() != null) {
            List<StudijskiProgram> programi = dto.getStudijskiProgramiIds().stream()
                    .map(id -> studijskiProgramRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Studijski program sa ID " + id + " nije pronađen.")))
                    .collect(Collectors.toList());
            tip.setStudijskiProgrami(new ArrayList<>(programi));
        }

        return tip;
    }

    private TipStudijaDTO mapToDTO(TipStudija tip) {
        List<Long> programIds = tip.getStudijskiProgrami() != null
                ? tip.getStudijskiProgrami().stream().map(StudijskiProgram::getId).collect(Collectors.toList())
                : new ArrayList<>();

        return new TipStudijaDTO(
                tip.getId(),
                tip.getTip(),
                tip.getKatedra() != null ? tip.getKatedra().getId() : null,
                programIds
        );
    }
}