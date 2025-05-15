package glavniPaket.service.predmet;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

import glavniPaket.model.predmet.Predmet;
import glavniPaket.repository.predmet.PredmetRepository;

public class PredmetService {
    private PredmetRepository predmetRepository;

    public PredmetService(PredmetRepository predmetRepository) {
        this.predmetRepository = predmetRepository;
    }

    public Iterable<Predmet> findAll() {
        return predmetRepository.findAll();
    }

    public Optional<Predmet> findById(Long id) {
        return predmetRepository.findById(id);
    }

    public Optional<Predmet> findByNaziv(String naziv) {
        return predmetRepository.findByNaziv(naziv);
    }

    public Predmet save(Predmet predmet) {
        return predmetRepository.save(predmet);
    }

    public void deleteById(Long id) {
        predmetRepository.deleteById(id);
    }

    public Predmet update(Long id, Predmet noviPodaci) {
        return predmetRepository.findById(id).map(predmet -> {
            predmet.setNaziv(noviPodaci.getNaziv());
            predmet.setEsts(noviPodaci.getEsts());
            predmet.setInformacijeOPredmetu(noviPodaci.getInformacijeOPredmetu());
            predmet.setProfesori(noviPodaci.getProfesori());
            predmet.setGodinaStudija(noviPodaci.getGodinaStudija());
            return predmetRepository.save(predmet);
        }).orElseThrow(() -> new EntityNotFoundException("Predmet sa ID " + id + " nije pronađen"));
    }
}
