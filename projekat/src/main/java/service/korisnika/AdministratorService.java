package service.korisnika;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.korisnika.Administrator;
import repository.korisnika.AdministratorRepository;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public AdministratorService() {
        super();
    }

    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public Administrator findById(int id) {
        return administratorRepository.findById(id).orElse(null);
    }

    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    public Administrator save(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    public void delete(Administrator administrator) {
        administratorRepository.delete(administrator);
    }

    public void deleteById(Integer id) {
        administratorRepository.deleteById(id);
    }
}
