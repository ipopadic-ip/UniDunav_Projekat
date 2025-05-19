package glavniPaket.service.predmet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.predmet.SilabusDTO;
import glavniPaket.model.predmet.Predmet;
import glavniPaket.model.predmet.Silabus;
import glavniPaket.repository.predmet.PredmetRepository;
import glavniPaket.repository.predmet.SilabusRepository;

@Service
public class SilabusService {

    private final SilabusRepository silabusRepository;
    private final PredmetRepository predmetRepository;

    @Autowired
    public SilabusService(SilabusRepository silabusRepository, PredmetRepository predmetRepository) {
        this.silabusRepository = silabusRepository;
        this.predmetRepository = predmetRepository;
    }

    public List<SilabusDTO> findAll() {
        return silabusRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public SilabusDTO findById(Long id) {
        Silabus silabus = silabusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silabus sa ID " + id + " nije pronađen."));
        return mapToDTO(silabus);
    }

    public SilabusDTO save(SilabusDTO dto) {
        Silabus silabus = mapToEntity(dto);
        return mapToDTO(silabusRepository.save(silabus));
    }

    public SilabusDTO update(Long id, SilabusDTO dto) {
        Silabus silabus = silabusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silabus sa ID " + id + " ne postoji."));
        silabus.setSadrzaj(dto.getSadrzaj());

        if (dto.getPredmetId() != null) {
            Predmet predmet = predmetRepository.findById(dto.getPredmetId())
                    .orElseThrow(() -> new RuntimeException("Predmet sa ID " + dto.getPredmetId() + " nije pronađen."));
            silabus.setPredmet(predmet);
        }

        return mapToDTO(silabusRepository.save(silabus));
    }

    public void delete(Long id) {
        if (!silabusRepository.existsById(id)) {
            throw new RuntimeException("Silabus sa ID " + id + " ne postoji.");
        }
        silabusRepository.deleteById(id);
    }

    // ============ Mapping =============

    private SilabusDTO mapToDTO(Silabus silabus) {
        return new SilabusDTO(
                silabus.getId(),
                silabus.getSadrzaj(),
                silabus.getPredmet() != null ? silabus.getPredmet().getId() : null
        );
    }

    private Silabus mapToEntity(SilabusDTO dto) {
        Silabus silabus = new Silabus();
        silabus.setId(dto.getId());
        silabus.setSadrzaj(dto.getSadrzaj());

        if (dto.getPredmetId() != null) {
            Predmet predmet = predmetRepository.findById(dto.getPredmetId())
                    .orElseThrow(() -> new RuntimeException("Predmet sa ID " + dto.getPredmetId() + " nije pronađen."));
            silabus.setPredmet(predmet);
        }

        return silabus;
    }
}
