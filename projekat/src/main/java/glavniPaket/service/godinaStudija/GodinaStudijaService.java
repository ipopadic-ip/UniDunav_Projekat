package glavniPaket.service.godinaStudija;

import java.util.Optional;

import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.repository.godinaStudija.GodinaStudijaRepository;
import jakarta.persistence.EntityNotFoundException;

public class GodinaStudijaService {
    private GodinaStudijaRepository godinaStudijaRepository;

    public GodinaStudijaService(GodinaStudijaRepository godinaStudijaRepository) {
        this.godinaStudijaRepository = godinaStudijaRepository;
    }

    public Iterable<GodinaStudija> findAll() {
        return godinaStudijaRepository.findAll();
    }

    public Optional<GodinaStudija> findById(Long id) {
        return godinaStudijaRepository.findById(id);
    }

    public GodinaStudija save(GodinaStudija godinaStudija) {
        return godinaStudijaRepository.save(godinaStudija);
    }

    public void deleteById(Long id) {
        godinaStudijaRepository.deleteById(id);
    }

    public GodinaStudija update(Long id, GodinaStudija noviPodaci) {
        return godinaStudijaRepository.findById(id).map(godinaStudija -> {
            godinaStudija.setGodina(noviPodaci.getGodina());
            godinaStudija.setStudijskiProgram(noviPodaci.getStudijskiProgram());
            godinaStudija.setPredmeti(noviPodaci.getPredmeti());
            return godinaStudijaRepository.save(godinaStudija);
        }).orElseThrow(() -> new EntityNotFoundException("Godina studija sa ID " + id + " nije pronađena"));
    }
}
