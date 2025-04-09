package glavniPaket.repository.katedra;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;

public interface KatedraRepository extends JpaRepository<Katedra, Integer>{
	// Pronalazi katedru po nazivu
    Optional<Katedra> findByNaziv(String naziv);

    // Pronalazi katedru čiji naziv počinje određenim slovima
    List<Katedra> findByNazivStartingWith(String prefix);
    
 // Pronalazi katedru po fakultetu
    Optional<Katedra> findByFakultet(Fakultet fakultet);

    // Custom JPQL upit za pronalazak katedru prema delu naziva
    @Query("SELECT k FROM Katedra k WHERE k.naziv LIKE %:keyword% ")
    List<Katedra> pretraziPoNazivu(String keyword);
}
