package glavniPaket.repository.korisnika;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import glavniPaket.model.korisnika.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	 	List<Student> findByGodinaUpisa(Date godinaUpisa);

	    List<Student> findByBrojIndeksa(String brojIndeksa);

}
