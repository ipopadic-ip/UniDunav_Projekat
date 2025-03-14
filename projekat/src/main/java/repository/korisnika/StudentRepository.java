package repository.korisnika;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import model.korisnika.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	 	List<Student> findByGodinaUpisa(Date godinaUpisa);

	    List<Student> findByBrojIndeksa(String brojIndeksa);

}
