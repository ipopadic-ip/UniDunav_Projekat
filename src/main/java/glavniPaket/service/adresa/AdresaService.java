package glavniPaket.service.adresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.model.adresa.Adresa;
import glavniPaket.repository.adresa.AdresaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdresaService {
    @Autowired
    private AdresaRepository adresaRepository;

    public List<Adresa> findAll() {
        return adresaRepository.findAll();
    }

    public Optional<Adresa> findById(Integer id) {
        return adresaRepository.findById(id);
    }

    public Adresa save(Adresa adresa) {
        return adresaRepository.save(adresa);
    }

    public void deleteById(Integer id) {
        adresaRepository.deleteById(id);
    }
}
