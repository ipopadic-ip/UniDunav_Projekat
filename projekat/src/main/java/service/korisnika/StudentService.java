package service.korisnika;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import model.korisnika.Student;
import repository.korisnika.StudentRepository;

public class StudentService {
	 	@Autowired
	    private StudentRepository studentRepository;

	   
	    public StudentService(StudentRepository studentRepository) {
	        this.studentRepository = studentRepository;
	    }

	    
	    public Student findById(Integer id) {
	        return studentRepository.findById(id).orElse(null);
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

	    
	    public void deleteById(Integer id) {
	        studentRepository.deleteById(id);
	    }

}
