package repository.predmet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.katedra.Katedra;
import model.korisnika.Profesor;
import model.predmet.Predmet;



public interface PredmetRepository extends JpaRepository<Predmet, Integer>{
	// Pronalazi predemet po nazivu
    Optional<Predmet> findByNaziv(String naziv);

    // Pronalazi predemet čiji naziv počinje određenim slovima
    List<Predmet> findByNazivStartingWith(String prefix);

 // Pronalazi predemet po katedri
    Optional<Predmet> findByKatedra(Katedra katedra);
    
    // Pronalazi predemet po profesoru
    Optional<Predmet> findByProfesoru(Profesor profesor);
    
 // Pronalazi predemet po ests
    Optional<Predmet> findByEsts(int ests);
    
    // Custom JPQL upit za pronalazak univerziteta prema delu naziva
    @Query("SELECT p FROM Predmet p WHERE p.naziv LIKE %:keyword% ")
    List<Predmet> pretraziPoNazivu(String keyword);
}
