package glavniPaket.repository.korisnika;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.univerzitet.Univerzitet;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
	List<Profesor> findByTitula(String titula);
    
    List<Profesor> findByFakultet(Fakultet fakultet);
    
    List<Profesor> findByUniverzitet(Univerzitet univerzitet);
    
}