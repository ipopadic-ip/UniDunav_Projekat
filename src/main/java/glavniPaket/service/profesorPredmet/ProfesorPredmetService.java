package glavniPaket.service.profesorPredmet;

import jakarta.persistence.EntityNotFoundException;

import glavniPaket.model.profesorPredmet.ProfesorPredmet;
import glavniPaket.repository.profesorPredmet.ProfesorPredmetRepository;

public class ProfesorPredmetService {

    private final ProfesorPredmetRepository profesorPredmetRepository;

    public ProfesorPredmetService(ProfesorPredmetRepository profesorPredmetRepository) {
        this.profesorPredmetRepository = profesorPredmetRepository;
    }

    public Iterable<ProfesorPredmet> findAll() {
        return profesorPredmetRepository.findAll();
    }

    public ProfesorPredmet findById(Long id) {
        return profesorPredmetRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("ProfesorPredmet sa ID " + id + " nije pronađen"));
    }

    public ProfesorPredmet save(ProfesorPredmet profesorPredmet) {
        return profesorPredmetRepository.save(profesorPredmet);
    }

    public ProfesorPredmet update(Long id, ProfesorPredmet noviPodaci) {
        return profesorPredmetRepository.findById(id).map(profesorPredmet -> {
            profesorPredmet.setProfesor(noviPodaci.getProfesor());
            profesorPredmet.setPredmet(noviPodaci.getPredmet());
            return profesorPredmetRepository.save(profesorPredmet);
        }).orElseThrow(() -> new EntityNotFoundException("ProfesorPredmet sa ID " + id + " nije pronađen"));
    }

    public void deleteById(Long id) {
        if (!profesorPredmetRepository.existsById(id)) {
            throw new EntityNotFoundException("ProfesorPredmet sa ID " + id + " nije pronađen");
        }
        profesorPredmetRepository.deleteById(id);
    }
}
