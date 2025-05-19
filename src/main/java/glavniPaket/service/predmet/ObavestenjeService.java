package glavniPaket.service.predmet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.predmet.ObavestenjeDTO;
import glavniPaket.model.predmet.Obavestenje;
import glavniPaket.repository.predmet.ObavestenjeRepository;
import glavniPaket.repository.predmet.PredmetRepository;

@Service
public class ObavestenjeService {

    private final ObavestenjeRepository obavestenjeRepository;
    private final PredmetRepository predmetRepository;

    @Autowired
    public ObavestenjeService(ObavestenjeRepository obavestenjeRepository, PredmetRepository predmetRepository) {
        this.obavestenjeRepository = obavestenjeRepository;
        this.predmetRepository = predmetRepository;
    }

    // === CRUD ===

    public List<ObavestenjeDTO> findAll() {
        return obavestenjeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ObavestenjeDTO findById(Long id) {
        Obavestenje obavestenje = obavestenjeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obaveštenje sa ID " + id + " nije pronađeno."));
        return mapToDTO(obavestenje);
    }

    public List<ObavestenjeDTO> findByPredmetId(Long predmetId) {
        return obavestenjeRepository.findByPredmetId(predmetId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ObavestenjeDTO save(ObavestenjeDTO dto) {
        Obavestenje obavestenje = mapToEntity(dto);
        return mapToDTO(obavestenjeRepository.save(obavestenje));
    }

    public ObavestenjeDTO update(Long id, ObavestenjeDTO dto) {
        Obavestenje existing = obavestenjeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obaveštenje sa ID " + id + " nije pronađeno."));

        Obavestenje updated = mapToEntity(dto);
        updated.setId(existing.getId());

        return mapToDTO(obavestenjeRepository.save(updated));
    }

    public void delete(Long id) {
        if (!obavestenjeRepository.existsById(id)) {
            throw new RuntimeException("Obaveštenje sa ID " + id + " ne postoji.");
        }
        obavestenjeRepository.deleteById(id);
    }

    // === MAPIRANJE ===

    private ObavestenjeDTO mapToDTO(Obavestenje o) {
        return new ObavestenjeDTO(
                o.getId(),
                o.getTekst(),
                o.getDatum(),
                o.getPredmet() != null ? o.getPredmet().getId() : null
        );
    }

    private Obavestenje mapToEntity(ObavestenjeDTO dto) {
        Obavestenje o = new Obavestenje();
        o.setId(dto.getId());
        o.setTekst(dto.getTekst());
        o.setDatum(dto.getDatum());

        if (dto.getPredmetId() != null) {
            o.setPredmet(predmetRepository.findById(dto.getPredmetId())
                    .orElseThrow(() -> new RuntimeException("Predmet sa ID " + dto.getPredmetId() + " nije pronađen.")));
        }

        return o;
    }
}