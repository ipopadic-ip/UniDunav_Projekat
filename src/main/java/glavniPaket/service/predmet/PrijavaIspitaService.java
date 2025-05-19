package glavniPaket.service.predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.predmet.PrijavaIspitaDTO;
import glavniPaket.model.predmet.PrijavaIspita;
import glavniPaket.repository.predmet.PohadjanjePredmetaRepository;
import glavniPaket.repository.predmet.PrijavaIspitaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrijavaIspitaService {

    private final PrijavaIspitaRepository prijavaRepo;
    private final PohadjanjePredmetaRepository pohadjanjeRepo;

    @Autowired
    public PrijavaIspitaService(PrijavaIspitaRepository prijavaRepo,
                                PohadjanjePredmetaRepository pohadjanjeRepo) {
        this.prijavaRepo = prijavaRepo;
        this.pohadjanjeRepo = pohadjanjeRepo;
    }

    public List<PrijavaIspitaDTO> findAll() {
        return prijavaRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PrijavaIspitaDTO findById(Long id) {
        return prijavaRepo.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Prijava ispita nije pronađena"));
    }

    public List<PrijavaIspitaDTO> findByPohadjanjeId(Long pohadjanjeId) {
        return prijavaRepo.findByPohadjanjeId(pohadjanjeId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PrijavaIspitaDTO> findByStatus(boolean status) {
        return prijavaRepo.findByStatus(status).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PrijavaIspitaDTO save(PrijavaIspitaDTO dto) {
        PrijavaIspita entity = mapToEntity(dto);
        PrijavaIspita saved = prijavaRepo.save(entity);
        return mapToDTO(saved);
    }

    public void delete(Long id) {
        prijavaRepo.deleteById(id);
    }

    // ================= MAPERI =================

    private PrijavaIspitaDTO mapToDTO(PrijavaIspita entity) {
        return new PrijavaIspitaDTO(
                entity.getId(),
                entity.getDatumPrijave(),
                entity.getDatumIspita(),
                entity.isStatus(),
                entity.getPohadjanje().getId()
        );
    }

    private PrijavaIspita mapToEntity(PrijavaIspitaDTO dto) {
        PrijavaIspita p = new PrijavaIspita();
        p.setId(dto.getId());
        p.setDatumPrijave(dto.getDatumPrijave());
        p.setDatumIspita(dto.getDatumIspita());
        p.setStatus(dto.isStatus());

        p.setPohadjanje(pohadjanjeRepo.findById(dto.getPohadjanjeId())
                .orElseThrow(() -> new RuntimeException("Pohađanje predmeta nije pronađeno")));

        return p;
    }
}
