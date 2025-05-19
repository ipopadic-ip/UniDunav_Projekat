package glavniPaket.service.predmet;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import glavniPaket.dto.predmet.PredmetDTO;
import glavniPaket.model.predmet.Obavestenje;
import glavniPaket.model.predmet.Predmet;
import glavniPaket.model.profesorPredmet.ProfesorPredmet;
import glavniPaket.repository.godinaStudija.GodinaStudijaRepository;
import glavniPaket.repository.predmet.ObavestenjeRepository;
import glavniPaket.repository.predmet.PredmetRepository;
import glavniPaket.repository.predmet.SilabusRepository;
import glavniPaket.repository.profesorPredmet.ProfesorPredmetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PredmetService {

    private final PredmetRepository predmetRepository;
    private final GodinaStudijaRepository godinaStudijaRepository;
    private final SilabusRepository silabusRepository;
    private final ObavestenjeRepository obavestenjeRepository;
    private final ProfesorPredmetRepository profesorPredmetRepository;
    private final PohadjanjePredmetaRepository pohadjanjePredmetaRepository;

    @Autowired
    public PredmetService(PredmetRepository predmetRepository,
                          GodinaStudijaRepository godinaStudijaRepository,
                          SilabusRepository silabusRepository,
                          ObavestenjeRepository obavestenjeRepository,
                          ProfesorPredmetRepository profesorPredmetRepository,
                          PohadjanjePredmetaRepository pohadjanjePredmetaRepository) {
        this.predmetRepository = predmetRepository;
        this.godinaStudijaRepository = godinaStudijaRepository;
        this.silabusRepository = silabusRepository;
        this.obavestenjeRepository = obavestenjeRepository;
        this.profesorPredmetRepository = profesorPredmetRepository;
        this.pohadjanjePredmetaRepository = pohadjanjePredmetaRepository;
    }

    // === CRUD ===

    public List<PredmetDTO> findAll() {
        return predmetRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PredmetDTO findById(Long id) {
        Predmet p = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet sa ID " + id + " nije pronađen."));
        return mapToDTO(p);
    }

    public PredmetDTO save(PredmetDTO dto) {
        Predmet p = mapToEntity(dto);
        return mapToDTO(predmetRepository.save(p));
    }

    public PredmetDTO update(Long id, PredmetDTO dto) {
        Predmet existing = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet sa ID " + id + " nije pronađen."));
        Predmet updated = mapToEntity(dto);
        updated.setId(id);
        return mapToDTO(predmetRepository.save(updated));
    }

    public void delete(Long id) {
        if (!predmetRepository.existsById(id)) {
            throw new RuntimeException("Predmet sa ID " + id + " ne postoji.");
        }
        predmetRepository.deleteById(id);
    }

    // === DODATNE METODE ===

    public Optional<PredmetDTO> findByNaziv(String naziv) {
        return predmetRepository.findByNaziv(naziv).map(this::mapToDTO);
    }

    public List<PredmetDTO> findByNazivStartingWith(String prefix) {
        return predmetRepository.findByNazivStartingWith(prefix).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PredmetDTO> findByEcts(int ects) {
        return predmetRepository.findByEcts(ects).map(this::mapToDTO);
    }

    public List<PredmetDTO> pretraziPoNazivu(String keyword) {
        return predmetRepository.pretraziPoNazivu(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // === MAPIRANJE ===

    private PredmetDTO mapToDTO(Predmet p) {
        return new PredmetDTO(
                p.getId(),
                p.getNaziv(),
                p.getEcts(),
                p.getInformacijeOPredmetu(),
                p.getGodinaStudija() != null ? p.getGodinaStudija().getId() : null,
                p.getSilabus() != null ? p.getSilabus().getId() : null,
                p.getObavestenja() != null ? p.getObavestenja().stream().map(Obavestenje::getId).collect(Collectors.toList()) : new ArrayList<>(),
                p.getProfesori() != null ? p.getProfesori().stream().map(ProfesorPredmet::getId).collect(Collectors.toList()) : new ArrayList<>(),
                p.getPohadjanja() != null ? p.getPohadjanja().stream().map(PohadjanjePredmeta::getId).collect(Collectors.toList()) : new ArrayList<>()
        );
    }

    private Predmet mapToEntity(PredmetDTO dto) {
        Predmet p = new Predmet();
        p.setId(dto.getId());
        p.setNaziv(dto.getNaziv());
        p.setEcts(dto.getEcts());
        p.setInformacijeOPredmetu(dto.getInformacijeOPredmetu());

        if (dto.getGodinaStudijaId() != null) {
            p.setGodinaStudija(godinaStudijaRepository.findById(dto.getGodinaStudijaId())
                    .orElseThrow(() -> new RuntimeException("Godina studija nije pronađena.")));
        }

        if (dto.getSilabusId() != null) {
            p.setSilabus(silabusRepository.findById(dto.getSilabusId())
                    .orElseThrow(() -> new RuntimeException("Silabus nije pronađen.")));
        }

        if (dto.getObavestenjeIds() != null) {
            p.setObavestenja(dto.getObavestenjeIds().stream()
                    .map(id -> obavestenjeRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Obaveštenje sa ID " + id + " nije pronađeno.")))
                    .collect(Collectors.toList()));
        }

        if (dto.getProfesorIds() != null) {
            p.setProfesori(dto.getProfesorIds().stream()
                    .map(id -> profesorPredmetRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Veza profesor-predmet sa ID " + id + " nije pronađena.")))
                    .collect(Collectors.toList()));
        }

        if (dto.getPohadjanjeIds() != null) {
            p.setPohadjanja(dto.getPohadjanjeIds().stream()
                    .map(id -> pohadjanjePredmetaRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Pohadjanje predmeta sa ID " + id + " nije pronađeno.")))
                    .collect(Collectors.toList()));
        }

        return p;
    }
}