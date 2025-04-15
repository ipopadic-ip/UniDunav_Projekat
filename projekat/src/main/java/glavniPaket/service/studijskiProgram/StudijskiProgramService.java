package glavniPaket.service.studijskiProgram;

import java.util.Optional;

import glavniPaket.model.studijskiProgram.StudijskiProgram;
import glavniPaket.repository.studijskiProgram.StudijskiProgramRepository;
import jakarta.persistence.EntityNotFoundException;

public class StudijskiProgramService {
    private StudijskiProgramRepository studijskiProgramRepository;

    public StudijskiProgramService(StudijskiProgramRepository studijskiProgramRepository) {
        this.studijskiProgramRepository = studijskiProgramRepository;
    }

    public Iterable<StudijskiProgram> findAll() {
        return studijskiProgramRepository.findAll();
    }

    public Optional<StudijskiProgram> findById(Long id) {
        return studijskiProgramRepository.findById(id);
    }

    public StudijskiProgram save(StudijskiProgram studijskiProgram) {
        return studijskiProgramRepository.save(studijskiProgram);
    }

    public void deleteById(Long id) {
        studijskiProgramRepository.deleteById(id);
    }

    public StudijskiProgram update(Long id, StudijskiProgram noviPodaci) {
        return studijskiProgramRepository.findById(id).map(studijskiProgram -> {
            studijskiProgram.setNaziv(noviPodaci.getNaziv());
            studijskiProgram.setOpis(noviPodaci.getOpis());
            studijskiProgram.setGodinaStudija(noviPodaci.getGodinaStudija());
            studijskiProgram.setTipStudija(noviPodaci.getTipStudija());
            studijskiProgram.setRukovodilac(noviPodaci.getRukovodilac());
            return studijskiProgramRepository.save(studijskiProgram);
        }).orElseThrow(() -> new EntityNotFoundException("Studijski program sa ID " + id + " nije pronađen"));
    }
}
