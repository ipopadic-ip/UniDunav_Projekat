package glavniPaket.service.predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.predmet.TipEvaluacijeDTO;
import glavniPaket.model.predmet.TipEvaluacije;
import glavniPaket.repository.predmet.TipEvaluacijeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipEvaluacijeService {

    private final TipEvaluacijeRepository tipRepo;

    @Autowired
    public TipEvaluacijeService(TipEvaluacijeRepository tipRepo) {
        this.tipRepo = tipRepo;
    }

    public List<TipEvaluacijeDTO> findAll() {
        return tipRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TipEvaluacijeDTO findById(Long id) {
        return tipRepo.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Tip evaluacije nije pronađen"));
    }

    public TipEvaluacijeDTO findByTip(String tip) {
        TipEvaluacije t = tipRepo.findByTip(tip);
        if (t == null) {
            throw new RuntimeException("Tip evaluacije sa nazivom '" + tip + "' nije pronađen");
        }
        return mapToDTO(t);
    }

    public TipEvaluacijeDTO save(TipEvaluacijeDTO dto) {
        TipEvaluacije entity = mapToEntity(dto);
        return mapToDTO(tipRepo.save(entity));
    }

    public void delete(Long id) {
        tipRepo.deleteById(id);
    }

    // ============ MAPERI ============

    private TipEvaluacijeDTO mapToDTO(TipEvaluacije entity) {
        return new TipEvaluacijeDTO(entity.getId(), entity.getTip());
    }

    private TipEvaluacije mapToEntity(TipEvaluacijeDTO dto) {
        TipEvaluacije t = new TipEvaluacije();
        t.setId(dto.getId());
        t.setTip(dto.getTip());
        return t;
    }
}
