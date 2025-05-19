package glavniPaket.service.godinaStudija;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.godinaStudija.GodinaStudijaDTO;
import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.model.predmet.Predmet;
import glavniPaket.repository.godinaStudija.GodinaStudijaRepository;
import glavniPaket.repository.predmet.PredmetRepository;
import glavniPaket.repository.studijskiProgram.StudijskiProgramRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GodinaStudijaService {

    private final GodinaStudijaRepository godinaStudijaRepository;
    private final StudijskiProgramRepository studijskiProgramRepository;
    private final PredmetRepository predmetRepository;

    @Autowired
    public GodinaStudijaService(GodinaStudijaRepository godinaStudijaRepository,
                                StudijskiProgramRepository studijskiProgramRepository,
                                PredmetRepository predmetRepository) {
        this.godinaStudijaRepository = godinaStudijaRepository;
        this.studijskiProgramRepository = studijskiProgramRepository;
        this.predmetRepository = predmetRepository;
    }

    // === CRUD ===

    public List<GodinaStudijaDTO> findAll() {
        return godinaStudijaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public GodinaStudijaDTO findById(Long id) {
        GodinaStudija godina = godinaStudijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Godina studija sa ID " + id + " nije pronađena."));
        return mapToDTO(godina);
    }

    public GodinaStudijaDTO save(GodinaStudijaDTO dto) {
        GodinaStudija godina = godinaStudijaRepository.save(mapToEntity(dto));
        return mapToDTO(godina);
    }

    public GodinaStudijaDTO update(Long id, GodinaStudijaDTO dto) {
        GodinaStudija existing = godinaStudijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Godina studija sa ID " + id + " nije pronađena."));
        GodinaStudija updated = mapToEntity(dto);
        updated.setId(existing.getId());
        return mapToDTO(godinaStudijaRepository.save(updated));
    }

    public void delete(Long id) {
        if (!godinaStudijaRepository.existsById(id)) {
            throw new RuntimeException("Godina studija sa ID " + id + " ne postoji.");
        }
        godinaStudijaRepository.deleteById(id);
    }

    // === MAPIRANJE ===

    private GodinaStudija mapToEntity(GodinaStudijaDTO dto) {
        GodinaStudija godina = new GodinaStudija();
        godina.setId(dto.getId());
        godina.setGodina(dto.getGodina());

        if (dto.getStudijskiProgramId() != null) {
            godina.setStudijskiProgram(studijskiProgramRepository.findById(dto.getStudijskiProgramId())
                    .orElseThrow(() -> new RuntimeException("Studijski program sa ID " + dto.getStudijskiProgramId() + " nije pronađen.")));
        }

        if (dto.getPredmetIds() != null) {
            List<Predmet> predmeti = dto.getPredmetIds().stream()
                    .map(id -> predmetRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Predmet sa ID " + id + " nije pronađen.")))
                    .collect(Collectors.toList());
            godina.setPredmeti(new ArrayList<>(predmeti));
        }

        return godina;
    }

    private GodinaStudijaDTO mapToDTO(GodinaStudija godina) {
        List<Long> predmetIds = godina.getPredmeti() != null
                ? godina.getPredmeti().stream().map(Predmet::getId).collect(Collectors.toList())
                : new ArrayList<>();

        return new GodinaStudijaDTO(
                godina.getId(),
                godina.getGodina(),
                godina.getStudijskiProgram() != null ? godina.getStudijskiProgram().getId() : null,
                predmetIds
        );
    }
}
