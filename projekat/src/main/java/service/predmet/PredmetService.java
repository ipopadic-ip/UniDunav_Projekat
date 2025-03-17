package service.predmet;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import model.fakultet.Fakultet;
import model.predmet.Predmet;
import repository.fakultet.FakultetRepository;
import repository.predmet.PredmetRepository;

public class PredmetService {
	private PredmetRepository predmetRepository;
	
	public PredmetService(PredmetRepository predmetRepository) {
		this.predmetRepository = predmetRepository;
	}
	
	public Iterable<Predmet> findAll() {
        return predmetRepository.findAll();
    }
	
	public Optional<Predmet> findById(Integer id) {
        return predmetRepository.findById(id);
    }
	
	public Optional<Predmet> findByNaziv(String naziv) {
        return predmetRepository.findByNaziv(naziv);
    }
	
	public Predmet save(Predmet fakultet) {
        return predmetRepository.save(fakultet);
    }
	
	public void deleteById(Integer id) {
		predmetRepository.deleteById(id);
    }
	
	public Predmet update(Integer id, Predmet noviPodaci) {
        return predmetRepository.findById(id).map(predmet -> {
        	predmet.setNaziv(predmet.getNaziv());
        	predmet.setEsts(predmet.getEsts());
        	predmet.setInformacijeOPredmetu(predmet.getInformacijeOPredmetu());
        	predmet.setProfesor(predmet.getProfesor());
        	predmet.setKatedra(predmet.getKatedra());
        	predmet.setProfesorPredmet(predmet.getProfesorPredmet());
            return predmetRepository.save(predmet);
        }).orElseThrow(() -> new EntityNotFoundException("Fakultet sa ID " + id + " nije pronadjen"));
    }
}
