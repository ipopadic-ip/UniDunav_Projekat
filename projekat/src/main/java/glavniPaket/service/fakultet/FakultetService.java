package glavniPaket.service.fakultet;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.repository.fakultet.FakultetRepository;

public class FakultetService {
    private FakultetRepository fakultetRepository;

    public FakultetService(FakultetRepository fakultetRepository) {
        this.fakultetRepository = fakultetRepository;
    }

    public Iterable<Fakultet> findAll() {
        return fakultetRepository.findAll();
    }

    public Optional<Fakultet> findById(Long id) {
        return fakultetRepository.findById(id);
    }

    public Optional<Fakultet> findByNaziv(String naziv) {
        return fakultetRepository.findByNaziv(naziv);
    }

    public Fakultet save(Fakultet fakultet) {
        return fakultetRepository.save(fakultet);
    }

    public void deleteById(Long id) {
        fakultetRepository.deleteById(id);
    }

    public Fakultet update(Long id, Fakultet noviPodaci) {
        return fakultetRepository.findById(id).map(fakultet -> {
            fakultet.setNaziv(noviPodaci.getNaziv());
            fakultet.setEmail(noviPodaci.getEmail());
            fakultet.setAdresa(noviPodaci.getAdresa());
            fakultet.setUniverzitet(noviPodaci.getUniverzitet());
            fakultet.setKatedre(noviPodaci.getKatedre());
            fakultet.setOpis(noviPodaci.getOpis());
            fakultet.setDekan(noviPodaci.getDekan());
            return fakultetRepository.save(fakultet);
        }).orElseThrow(() -> new EntityNotFoundException("Fakultet sa ID " + id + " nije pronađen"));
    }
}
