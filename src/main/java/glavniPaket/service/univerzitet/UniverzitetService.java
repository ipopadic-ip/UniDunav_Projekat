package glavniPaket.service.univerzitet;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.repository.univerzitet.UniverzitetRepository;

@Service
public class UniverzitetService {

    private final UniverzitetRepository univerzitetRepository;

    public UniverzitetService(UniverzitetRepository univerzitetRepository) {
        this.univerzitetRepository = univerzitetRepository;
    }

    public Iterable<Univerzitet> findAll() {
        return univerzitetRepository.findAll();
    }

    public Optional<Univerzitet> findById(Long id) {
        return univerzitetRepository.findById(id);
    }

    public Optional<Univerzitet> findByNaziv(String naziv) {
        return univerzitetRepository.findByNaziv(naziv);
    }

    public Univerzitet save(Univerzitet univerzitet) {
        return univerzitetRepository.save(univerzitet);
    }

    public void deleteById(Long id) {
        univerzitetRepository.deleteById(id);
    }

    public Univerzitet update(Long id, Univerzitet noviPodaci) {
        return univerzitetRepository.findById(id).map(univerzitet -> {
            univerzitet.setNaziv(noviPodaci.getNaziv());
            univerzitet.setBrojTelefona(noviPodaci.getBrojTelefona());
            univerzitet.setEmail(noviPodaci.getEmail());
            univerzitet.setOpis(noviPodaci.getOpis());
            univerzitet.setFakulteti(noviPodaci.getFakulteti());
            return univerzitetRepository.save(univerzitet);
        }).orElseThrow(() -> new EntityNotFoundException("Univerzitet sa ID " + id + " nije pronađen"));
    }
}
