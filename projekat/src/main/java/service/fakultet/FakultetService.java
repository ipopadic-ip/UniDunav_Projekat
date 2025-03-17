package service.fakultet;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import model.fakultet.Fakultet;
import model.univerzitet.Univerzitet;
import repository.fakultet.FakultetRepository;
import repository.univerzitet.UniverzitetRepository;

public class FakultetService {
	private FakultetRepository fakultetRepository;
	
	public FakultetService(FakultetRepository fakultetRepository) {
		this.fakultetRepository = fakultetRepository;
	}
	
	public Iterable<Fakultet> findAll() {
        return fakultetRepository.findAll();
    }
	
	public Optional<Fakultet> findById(Integer id) {
        return fakultetRepository.findById(id);
    }
	
	public Optional<Fakultet> findByNaziv(String naziv) {
        return fakultetRepository.findByNaziv(naziv);
    }
	
	public Fakultet save(Fakultet fakultet) {
        return fakultetRepository.save(fakultet);
    }
	
	public void deleteById(Integer id) {
		fakultetRepository.deleteById(id);
    }
	
	public Fakultet update(Integer id, Fakultet noviPodaci) {
        return fakultetRepository.findById(id).map(fakultet -> {
            fakultet.setNaziv(fakultet.getNaziv());
            fakultet.setEmail(fakultet.getEmail());
            fakultet.setUniverzitet(fakultet.getUniverzitet());
            fakultet.setKatedre(fakultet.getKatedre());
            fakultet.setOpis(fakultet.getOpis());
            return fakultetRepository.save(fakultet);
        }).orElseThrow(() -> new EntityNotFoundException("Fakultet sa ID " + id + " nije pronadjen"));
    }
}
