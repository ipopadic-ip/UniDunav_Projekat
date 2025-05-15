package glavniPaket.service.katedra;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.repository.katedra.KatedraRepository;

public class KatedraService {

	private KatedraRepository katedraRepository;
	
	public KatedraService(KatedraRepository katedraRepository) {
		this.katedraRepository = katedraRepository;
	}
	
	public Iterable<Katedra> findAll() {
        return katedraRepository.findAll();
    }
	
	public Optional<Katedra> findById(Long id) {
        return katedraRepository.findById(id);
    }
	
	public Optional<Katedra> findByNaziv(String naziv) {
        return katedraRepository.findByNaziv(naziv);
    }
	
	public Katedra save(Katedra katedra) {
        return katedraRepository.save(katedra);
    }
	
	public void deleteById(Long id) {
		katedraRepository.deleteById(id);
    }
	
	public Katedra update(Long id, Katedra noviPodaci) {
        return katedraRepository.findById(id).map(katedra -> {
        	katedra.setNaziv(katedra.getNaziv());
        	katedra.setPredmeti(katedra.getPredmeti());
        	katedra.setOpis(katedra.getOpis());
        	katedra.setTipoviStudija(katedra.getTipoviStudija());
        	katedra.setSefKatedre(noviPodaci.getSefKatedre());
            return katedraRepository.save(katedra);
        }).orElseThrow(() -> new EntityNotFoundException("Katedra sa ID " + id + " nije pronadjen"));
    }
}
