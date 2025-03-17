package service.korisnika;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.fakultet.Fakultet;
import model.korisnika.Profesor;
import model.univerzitet.Univerzitet;
import repository.korisnika.ProfesorRepository;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public ProfesorService() {
        super();
    }

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public Profesor findById(int id) {
        return profesorRepository.findById(id).orElse(null);
    }

    public Iterable<Profesor> findAll() {
        return profesorRepository.findAll();
    }

    public Iterable<Profesor> findByTitula(String titula) {
        return profesorRepository.findByTitula(titula);
    }

    public Iterable<Profesor> findByFakultet(Fakultet fakultet) {
        return profesorRepository.findByFakultet(fakultet);
    }

    public Iterable<Profesor> findByUniverzitet(Univerzitet univerzitet) {
        return profesorRepository.findByUniverzitet(univerzitet);
    }

    public Profesor save(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public void delete(Profesor profesor) {
        profesorRepository.delete(profesor);
    }

    public void deleteById(Integer id) {
        profesorRepository.deleteById(id);
    }
}
