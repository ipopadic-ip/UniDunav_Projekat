package glavniPaket.service.univerzitet;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.repository.univerzitet.UniverzitetRepository;

public class UniverzitetService {

	private UniverzitetRepository univerzitetRepository;
	
	public UniverzitetService(UniverzitetRepository univerzitetRepository) {
		this.univerzitetRepository = univerzitetRepository;
	}
	
	public Iterable<Univerzitet> findAll() {
        return univerzitetRepository.findAll();
    }
	
	public Optional<Univerzitet> findById(Integer id) {
        return univerzitetRepository.findById(id);
    }
	
	public Optional<Univerzitet> findByNaziv(String naziv) {
        return univerzitetRepository.findByNaziv(naziv);
    }
	
	public Univerzitet save(Univerzitet univerzitet) {
        return univerzitetRepository.save(univerzitet);
    }
	
	public void deleteById(Integer id) {
		univerzitetRepository.deleteById(id);
    }
	
	public Univerzitet update(Integer id, Univerzitet noviPodaci) {
        return univerzitetRepository.findById(id).map(univerzitet -> {
            univerzitet.setNaziv(univerzitet.getNaziv());
            univerzitet.setBrojTelefona(univerzitet.getBrojTelefona());
            univerzitet.setEmail(univerzitet.getEmail());
            univerzitet.setOpis(univerzitet.getOpis());
            univerzitet.setFakulteti(univerzitet.getFakulteti());
            return univerzitetRepository.save(univerzitet);
        }).orElseThrow(() -> new EntityNotFoundException("Univerzitet sa ID " + id + " nije pronadjen"));
    }
}
