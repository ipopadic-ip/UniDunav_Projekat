package glavniPaket.service.predmet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.predmet.PohadjanjePredmetaDTO;
import glavniPaket.model.korisnika.Student;
import glavniPaket.model.predmet.PohadjanjePredmeta;
import glavniPaket.model.predmet.Predmet;
import glavniPaket.repository.korisnika.StudentRepository;
import glavniPaket.repository.predmet.PohadjanjePredmetaRepository;
import glavniPaket.repository.predmet.PredmetRepository;

@Service
public class PohadjanjePredmetaService {

    private final PohadjanjePredmetaRepository pohadjanjeRepository;
    private final StudentRepository studentRepository;
    private final PredmetRepository predmetRepository;

    @Autowired
    public PohadjanjePredmetaService(PohadjanjePredmetaRepository pohadjanjeRepository,
                                     StudentRepository studentRepository,
                                     PredmetRepository predmetRepository) {
        this.pohadjanjeRepository = pohadjanjeRepository;
        this.studentRepository = studentRepository;
        this.predmetRepository = predmetRepository;
    }

    public List<PohadjanjePredmetaDTO> findAll() {
        return pohadjanjeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PohadjanjePredmetaDTO findById(Long id) {
        PohadjanjePredmeta p = pohadjanjeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pohadjanje nije pronađeno"));
        return mapToDTO(p);
    }

    public List<PohadjanjePredmetaDTO> findByStudentId(Long studentId) {
        return pohadjanjeRepository.findByStudentId(studentId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PohadjanjePredmetaDTO> findByPredmetId(Long predmetId) {
        return pohadjanjeRepository.findByPredmetId(predmetId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PohadjanjePredmetaDTO save(PohadjanjePredmetaDTO dto) {
        PohadjanjePredmeta entity = mapToEntity(dto);
        PohadjanjePredmeta saved = pohadjanjeRepository.save(entity);
        return mapToDTO(saved);
    }

    public void delete(Long id) {
        pohadjanjeRepository.deleteById(id);
    }

    // ================= MAPERI =================

    private PohadjanjePredmetaDTO mapToDTO(PohadjanjePredmeta p) {
        return new PohadjanjePredmetaDTO(
                p.getId(),
                p.getOcena(),
                p.getBrojPolaganja(),
                p.isAktivan(),
                p.getDatumPocetka(),
                p.getDatumZavrsetka(),
                p.getStudent().getId(),
                p.getPredmet().getId(),
                p.getPrijavePrestupa().stream().map(pr -> pr.getId()).collect(Collectors.toList()),
                p.getPrijaveIspita().stream().map(pi -> pi.getId()).collect(Collectors.toList()),
                p.getEvaluacije().stream().map(ev -> ev.getId()).collect(Collectors.toList())
        );
    }

    private PohadjanjePredmeta mapToEntity(PohadjanjePredmetaDTO dto) {
        PohadjanjePredmeta p = new PohadjanjePredmeta();
        p.setId(dto.getId());
        p.setOcena(dto.getOcena());
        p.setBrojPolaganja(dto.getBrojPolaganja());
        p.setAktivan(dto.isAktivan());
        p.setDatumPocetka(dto.getDatumPocetka());
        p.setDatumZavrsetka(dto.getDatumZavrsetka());

        p.setStudent(studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student nije pronađen")));

        p.setPredmet(predmetRepository.findById(dto.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen")));

        return p;
    }
}