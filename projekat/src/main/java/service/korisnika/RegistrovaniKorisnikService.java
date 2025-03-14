package service.korisnika;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import repository.korisnika.RegistrovaniKorisnikRepository;
import model.korisnika.*;

@Service
public class RegistrovaniKorisnikService {
    
    private final RegistrovaniKorisnikRepository registrovaniKorisnikRepository;
    
    @Autowired
    public RegistrovaniKorisnikService(RegistrovaniKorisnikRepository registrovaniKorisnikRepository) {
        this.registrovaniKorisnikRepository = registrovaniKorisnikRepository;
    }
    
    public Iterable<RegistrovaniKorisnik> findAll() {
        return registrovaniKorisnikRepository.findAll();
    }
    
    public Optional<RegistrovaniKorisnik> findById(Integer id) {
        return registrovaniKorisnikRepository.findById(id);
    }
    
    public RegistrovaniKorisnik save(RegistrovaniKorisnik korisnik) {
        return registrovaniKorisnikRepository.save(korisnik);
    }
    
    public void deleteById(Integer id) {
        registrovaniKorisnikRepository.deleteById(id);
    }
    
    public RegistrovaniKorisnik update(Integer id, RegistrovaniKorisnik noviPodaci) {
        return registrovaniKorisnikRepository.findById(id).map(korisnik -> {
            korisnik.setIme(noviPodaci.getIme());
            korisnik.setPrezime(noviPodaci.getPrezime());
            korisnik.setKorisnickoIme(noviPodaci.getKorisnickoIme());
            korisnik.setDatumRodjenja(noviPodaci.getDatumRodjenja());
            korisnik.setMestoRodjenja(noviPodaci.getMestoRodjenja());
            korisnik.setJmbg(noviPodaci.getJmbg());
            korisnik.setLozinka(noviPodaci.getLozinka());
            korisnik.setEmail(noviPodaci.getEmail());
            return registrovaniKorisnikRepository.save(korisnik);
        }).orElseThrow(() -> new EntityNotFoundException("Registrovani korisnik sa ID " + id + " nije pronadjen"));
    }
}

