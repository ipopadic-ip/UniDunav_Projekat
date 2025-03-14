package repository.korisnika;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import model.korisnika.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
	List<Profesor> findByTitula(String titula);
    
    List<Profesor> findByFakultet(Fakultet fakultet);
    
    List<Profesor> findByUniverzitet(Univerzitet univerzitet);
    
}
