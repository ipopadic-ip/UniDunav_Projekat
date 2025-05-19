package glavniPaket.service.predmet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.predmet.EvaluacijaZnanjaDTO;
import glavniPaket.dto.predmet.PohadjanjePredmetaDTO;
import glavniPaket.dto.predmet.TipEvaluacijeDTO;
import glavniPaket.model.predmet.EvaluacijaZnanja;
import glavniPaket.repository.predmet.EvaluacijaZnanjaRepository;
import glavniPaket.repository.predmet.PohadjanjePredmetaRepository;
import glavniPaket.repository.predmet.TipEvaluacijeRepository;

@Service
public class EvaluacijaZnanjaService {

    private final EvaluacijaZnanjaRepository evaluacijaRepo;
    private final TipEvaluacijeRepository tipRepo;
    private final PohadjanjePredmetaRepository pohadjanjeRepo;

    @Autowired
    public EvaluacijaZnanjaService(EvaluacijaZnanjaRepository evaluacijaRepo,
                                   TipEvaluacijeRepository tipRepo,
                                   PohadjanjePredmetaRepository pohadjanjeRepo) {
        this.evaluacijaRepo = evaluacijaRepo;
        this.tipRepo = tipRepo;
        this.pohadjanjeRepo = pohadjanjeRepo;
    }

    public List<EvaluacijaZnanjaDTO> findAll() {
        return evaluacijaRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EvaluacijaZnanjaDTO findById(Long id) {
        return evaluacijaRepo.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Evaluacija nije pronađena"));
    }

    public List<EvaluacijaZnanjaDTO> findByPohadjanjeId(Long pohadjanjeId) {
        return evaluacijaRepo.findByPohadjanjeId(pohadjanjeId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<EvaluacijaZnanjaDTO> findByTipEvaluacijeId(Long tipId) {
        return evaluacijaRepo.findByTipEvaluacijeId(tipId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EvaluacijaZnanjaDTO save(EvaluacijaZnanjaDTO dto) {
        EvaluacijaZnanja entity = mapToEntity(dto);
        return mapToDTO(evaluacijaRepo.save(entity));
    }

    public void delete(Long id) {
        evaluacijaRepo.deleteById(id);
    }

    // ================= MAPERI =================

    private EvaluacijaZnanjaDTO mapToDTO(EvaluacijaZnanja e) {
        TipEvaluacijeDTO tipDto = new TipEvaluacijeDTO(e.getTipEvaluacije().getId(), e.getTipEvaluacije().getTip());

        PohadjanjePredmetaDTO pohadjanjeDto = new PohadjanjePredmetaDTO(
                e.getPohadjanje().getId(),
                e.getPohadjanje().getOcena(),
                e.getPohadjanje().getBrojPolaganja(),
                e.getPohadjanje().isAktivan(),
                e.getPohadjanje().getDatumPocetka(),
                e.getPohadjanje().getDatumZavrsetka(),
                e.getPohadjanje().getStudent().getId(),
                e.getPohadjanje().getPredmet().getId(),
                null, // lista prijava prestupa
                null, // lista prijava ispita
                null  // lista evaluacija
        );

        return new EvaluacijaZnanjaDTO(
                e.getId(),
                e.getVremePocetka(),
                e.getBrojBodova(),
                tipDto,
                pohadjanjeDto
        );
    }

    private EvaluacijaZnanja mapToEntity(EvaluacijaZnanjaDTO dto) {
        EvaluacijaZnanja e = new EvaluacijaZnanja();
        e.setId(dto.getId());
        e.setVremePocetka(dto.getVremePocetka());
        e.setBrojBodova(dto.getBrojBodova());

        e.setTipEvaluacije(tipRepo.findById(dto.getTipEvaluacije().getId())
                .orElseThrow(() -> new RuntimeException("Tip evaluacije nije pronađen")));

        e.setPohadjanje(pohadjanjeRepo.findById(dto.getPohadjanje().getId())
                .orElseThrow(() -> new RuntimeException("Pohađanje predmeta nije pronađeno")));

        return e;
    }
}
