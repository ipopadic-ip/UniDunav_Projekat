package glavniPaket.service.predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.predmet.PrijavaPrestupaDTO;
import glavniPaket.model.predmet.PrijavaPrestupa;
import glavniPaket.repository.predmet.PohadjanjePredmetaRepository;
import glavniPaket.repository.predmet.PrijavaPrestupaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrijavaPrestupaService {

    private final PrijavaPrestupaRepository prijavaRepo;
    private final PohadjanjePredmetaRepository pohadjanjeRepo;

    @Autowired
    public PrijavaPrestupaService(PrijavaPrestupaRepository prijavaRepo,
                                  PohadjanjePredmetaRepository pohadjanjeRepo) {
        this.prijavaRepo = prijavaRepo;
        this.pohadjanjeRepo = pohadjanjeRepo;
    }

    public List<PrijavaPrestupaDTO> findAll() {
        return prijavaRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PrijavaPrestupaDTO findById(Long id) {
        return prijavaRepo.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Prijava prestupa nije pronađena"));
    }

    public List<PrijavaPrestupaDTO> findByPohadjanjeId(Long pohadjanjeId) {
        return prijavaRepo.findByPohadjanjeId(pohadjanjeId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PrijavaPrestupaDTO save(PrijavaPrestupaDTO dto) {
        PrijavaPrestupa entity = mapToEntity(dto);
        PrijavaPrestupa saved = prijavaRepo.save(entity);
        return mapToDTO(saved);
    }

    public void delete(Long id) {
        prijavaRepo.deleteById(id);
    }

    // ========== MAPERI ==========

    private PrijavaPrestupaDTO mapToDTO(PrijavaPrestupa entity) {
        return new PrijavaPrestupaDTO(
                entity.getId(),
                entity.getOpis(),
                entity.getDatum(),
                entity.getPohadjanje().getId()
        );
    }

    private PrijavaPrestupa mapToEntity(PrijavaPrestupaDTO dto) {
        PrijavaPrestupa p = new PrijavaPrestupa();
        p.setId(dto.getId());
        p.setOpis(dto.getOpis());
        p.setDatum(dto.getDatum());

        p.setPohadjanje(pohadjanjeRepo.findById(dto.getPohadjanjeId())
                .orElseThrow(() -> new RuntimeException("Pohađanje predmeta nije pronađeno")));

        return p;
    }
}
