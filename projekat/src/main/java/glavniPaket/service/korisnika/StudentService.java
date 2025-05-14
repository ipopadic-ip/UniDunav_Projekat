package glavniPaket.service.korisnika;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.model.korisnika.Student;
import glavniPaket.repository.korisnika.StudentRepository;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> findById(Long id) {  // Promenjen tip sa Integer na Long
        return studentRepository.findById(id);
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public Iterable<Student> findByBrojIndeksa(String brojIndeksa) {
        return studentRepository.findByBrojIndeksa(brojIndeksa);
    }

    public Iterable<Student> findByGodinaUpisa(Date godinaUpisa) {
        return studentRepository.findByGodinaUpisa(godinaUpisa);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteById(Long id) {  // Promenjen tip sa Integer na Long
        studentRepository.deleteById(id);
    }
}
