package glavniPaket.service.korisnika;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.profesorPredmet.ProfesorPredmet;
import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.repository.departman.DepartmanRepository;
import glavniPaket.repository.fakultet.FakultetRepository;
import glavniPaket.repository.katedra.KatedraRepository;
import glavniPaket.repository.korisnika.ProfesorRepository;
import glavniPaket.repository.korisnika.RegistrovaniKorisnikRepository;
import glavniPaket.repository.profesorPredmet.ProfesorPredmetRepository;
import glavniPaket.repository.studijskiProgram.StudijskiProgramRepository;
import glavniPaket.repository.univerzitet.UniverzitetRepository;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private RegistrovaniKorisnikRepository korisnikRepository;
    @Autowired
    private ProfesorPredmetRepository profesorPredmetRepository;
    @Autowired
    private UniverzitetRepository univerzitetRepository;
    @Autowired
    private FakultetRepository fakultetRepository;
    @Autowired
    private DepartmanRepository departmanRepository;
    @Autowired
    private KatedraRepository katedraRepository;
    @Autowired
    private StudijskiProgramRepository studijskiProgramRepository;

    public List<ProfesorDTO> findAll() {
        return profesorRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ProfesorDTO findById(Long id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        return mapToDTO(profesor);
    }

    public ProfesorDTO save(ProfesorDTO dto) {
        Profesor profesor = profesorRepository.save(mapToEntity(dto));
        return mapToDTO(profesor);
    }

    public ProfesorDTO update(Long id, ProfesorDTO dto) {
        Profesor postojeci = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        Profesor azuriran = mapToEntity(dto);
        azuriran.setId(id);
        return mapToDTO(profesorRepository.save(azuriran));
    }

    public void delete(Long id) {
        profesorRepository.deleteById(id);
    }

    // === MAPIRANJE ===

    private ProfesorDTO mapToDTO(Profesor p) {
        Set<Long> predmetIds = p.getPredmeti() != null
                ? p.getPredmeti().stream().map(ProfesorPredmet::getId).collect(Collectors.toSet())
                : new HashSet<>();

        return new ProfesorDTO(
                p.getId(),
                p.getTitula(),
                p.getBiografija(),
                p.getKorisnik() != null ? p.getKorisnik().getId() : null,
                predmetIds,
                p.getUniverzitet() != null ? p.getUniverzitet().getId() : null,
                p.getFakultet() != null ? p.getFakultet().getId() : null,
                p.getDepartman() != null ? p.getDepartman().getId() : null,
                p.getKatedra() != null ? p.getKatedra().getId() : null,
                p.getStudijskiProgram() != null ? p.getStudijskiProgram().getId() : null
        );
    }

    private Profesor mapToEntity(ProfesorDTO dto) {
        Profesor p = new Profesor();
        p.setId(dto.getId());
        p.setTitula(dto.getTitula());
        p.setBiografija(dto.getBiografija());

        if (dto.getKorisnikId() != null) {
            p.setKorisnik(korisnikRepository.findById(dto.getKorisnikId())
                    .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen")));
        }

        if (dto.getProfesorPredmetIds() != null) {
            Set<ProfesorPredmet> predmeti = dto.getProfesorPredmetIds().stream()
                    .map(id -> profesorPredmetRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("ProfesorPredmet ID ne postoji: " + id)))
                    .collect(Collectors.toSet());
            p.setPredmeti(predmeti);
        }

        if (dto.getUniverzitetId() != null) {
            p.setUniverzitet(univerzitetRepository.findById(dto.getUniverzitetId())
                    .orElse(null));
        }

        if (dto.getFakultetId() != null) {
            p.setFakultet(fakultetRepository.findById(dto.getFakultetId())
                    .orElse(null));
        }

        if (dto.getDepartmanId() != null) {
            p.setDepartman(departmanRepository.findById(dto.getDepartmanId())
                    .orElse(null));
        }

        if (dto.getKatedraId() != null) {
            p.setKatedra(katedraRepository.findById(dto.getKatedraId())
                    .orElse(null));
        }

        if (dto.getStudijskiProgramId() != null) {
            p.setStudijskiProgram(studijskiProgramRepository.findById(dto.getStudijskiProgramId())
                    .orElse(null));
        }

        return p;
    }
}