package glavniPaket.service.korisnika;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.model.korisnika.Osoblje;
import glavniPaket.repository.korisnika.OsobljeRepository;

@Service
public class OsobljeService {

    @Autowired
    private OsobljeRepository osobljeRepository;

    public OsobljeService() {
        super();
    }

    public OsobljeService(OsobljeRepository osobljeRepository) {
        this.osobljeRepository = osobljeRepository;
    }

    public Osoblje findById(int id) {
        return osobljeRepository.findById(id).orElse(null);
    }

    public List<Osoblje> findAll() {
        return osobljeRepository.findAll();
    }

    public Osoblje save(Osoblje osoblje) {
        return osobljeRepository.save(osoblje);
    }

    public void delete(Osoblje osoblje) {
        osobljeRepository.delete(osoblje);
    }

    public void deleteById(Integer id) {
        osobljeRepository.deleteById(id);
    }
}