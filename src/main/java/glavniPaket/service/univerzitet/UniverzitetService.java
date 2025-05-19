package glavniPaket.service.univerzitet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.repository.univerzitet.UniverzitetRepository;

@Service
public class UniverzitetService {

    private final UniverzitetRepository univerzitetRepository;

    @Autowired
    public UniverzitetService(UniverzitetRepository univerzitetRepository) {
        this.univerzitetRepository = univerzitetRepository;
    }

    // === Standardne CRUD operacije ===

    public List<Univerzitet> findAll() {
        return univerzitetRepository.findAll();
    }

    public Univerzitet findById(Long id) {
        return univerzitetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Univerzitet sa ID " + id + " nije pronađen."));
    }

    public Univerzitet save(Univerzitet univerzitet) {
        return univerzitetRepository.save(univerzitet);
    }

    public Univerzitet update(Long id, Univerzitet updated) {
        Univerzitet existing = findById(id);

        existing.setNaziv(updated.getNaziv());
        existing.setEmail(updated.getEmail());
        existing.setBrojTelefona(updated.getBrojTelefona());
        existing.setOpis(updated.getOpis());

        // Ažuriranje kompleksnih povezanih entiteta
        existing.setAdresa(updated.getAdresa());
        existing.setRektor(updated.getRektor());

        // Fakulteti se obično ne ažuriraju direktno kroz univerzitet jer je to @OneToMany (složenije)
        // Ako želiš, možemo implementirati logiku za dodavanje/brisanje fakulteta posebno

        return univerzitetRepository.save(existing);
    }

    public void delete(Long id) {
        if (!univerzitetRepository.existsById(id)) {
            throw new RuntimeException("Ne postoji univerzitet sa ID " + id);
        }
        univerzitetRepository.deleteById(id);
    }

    // === Dodatne metode iz tvojeg custom repository-ja ===

    public Optional<Univerzitet> findByNaziv(String naziv) {
        return univerzitetRepository.findByNaziv(naziv);
    }

    public List<Univerzitet> findByNazivStartingWith(String prefix) {
        return univerzitetRepository.findByNazivStartingWith(prefix);
    }

    public boolean existsByEmail(String email) {
        return univerzitetRepository.existsByEmail(email);
    }

    public List<Univerzitet> pretraziPoNazivu(String keyword) {
        return univerzitetRepository.pretraziPoNazivu(keyword);
    }

}