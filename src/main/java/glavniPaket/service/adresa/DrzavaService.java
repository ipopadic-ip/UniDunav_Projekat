package glavniPaket.service.adresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.model.adresa.Drzava;
import glavniPaket.repository.adresa.DrzavaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DrzavaService {
    @Autowired
    private DrzavaRepository drzavaRepository;

    public List<Drzava> findAll() {
        return drzavaRepository.findAll();
    }

    public Optional<Drzava> findById(Integer id) {
        return drzavaRepository.findById(id);
    }

    public Drzava save(Drzava drzava) {
        return drzavaRepository.save(drzava);
    }

    public void deleteById(Integer id) {
        drzavaRepository.deleteById(id);
    }
}
