package glavniPaket.service.korisnika;

import glavniPaket.model.korisnika.PravoPristupa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import glavniPaket.repository.korisnika.*;

import java.util.List;
import java.util.Optional;

@Service
public class PravoPristupaService {

    @Autowired
    private PravoPristupaRepository pravoRepo;

    public List<PravoPristupa> findAll() {
        return pravoRepo.findAll();
    }

    public Optional<PravoPristupa> findById(Integer id) {
        return pravoRepo.findById(id);
    }

    public PravoPristupa save(PravoPristupa pravo) {
        return pravoRepo.save(pravo);
    }

    public void deleteById(Integer id) {
        pravoRepo.deleteById(id);
    }
}