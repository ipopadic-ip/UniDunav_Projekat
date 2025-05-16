package glavniPaket.service.tipStudija;

import java.util.Optional;

import org.springframework.stereotype.Service;

import glavniPaket.model.tipStudija.TipStudija;
import glavniPaket.repository.tipStudija.TipStudijaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TipStudijaService {
	private TipStudijaRepository tipStudijaRepository;

    public TipStudijaService(TipStudijaRepository tipStudijaRepository) {
        this.tipStudijaRepository = tipStudijaRepository;
    }

    public Iterable<TipStudija> findAll() {
        return tipStudijaRepository.findAll();
    }

    public Optional<TipStudija> findById(Long id) {
        return tipStudijaRepository.findById(id);
    }

//    public Optional<TipStudija> findByNaziv(String naziv) {
//        return tipStudijaRepository.findByNaziv(naziv);
//    }

    public TipStudija save(TipStudija tipStudija) {
        return tipStudijaRepository.save(tipStudija);
    }

    public void deleteById(Long id) {
    	tipStudijaRepository.deleteById(id);
    }

    public TipStudija update(Long id, TipStudija noviPodaci) {
        return tipStudijaRepository.findById(id).map(tipStudija -> {
        	tipStudija.setTip(noviPodaci.getTip());
        	tipStudija.setKatedra(noviPodaci.getKatedra());
        	tipStudija.setStudijskiProgrami(noviPodaci.getStudijskiProgrami());
        	return tipStudijaRepository.save(tipStudija);
        }).orElseThrow(() -> new EntityNotFoundException("Predmet sa ID " + id + " nije pronađen"));
    }
    
    
}
