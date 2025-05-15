package glavniPaket.service.adresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.model.adresa.Mesto;
import glavniPaket.repository.adresa.MestoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MestoService {
    @Autowired
    private MestoRepository mestoRepository;

    public List<Mesto> findAll() {
        return mestoRepository.findAll();
    }

    public Optional<Mesto> findById(Integer id) {
        return mestoRepository.findById(id);
    }

    public Mesto save(Mesto mesto) {
        return mestoRepository.save(mesto);
    }

    public void deleteById(Integer id) {
        mestoRepository.deleteById(id);
    }
}
