package glavniPaket.repository.univerzitet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import glavniPaket.model.univerzitet.Univerzitet;

public interface UniverzitetRepository extends JpaRepository<Univerzitet, Integer> {
	  // Pronalazi univerzitet po nazivu
    Optional<Univerzitet> findByNaziv(String naziv);

    // Pronalazi univerzitet čiji naziv počinje određenim slovima
    List<Univerzitet> findByNazivStartingWith(String prefix);

    // Provera da li univerzitet postoji na osnovu email-a
    boolean existsByEmail(String email);

    // Custom JPQL upit za pronalazak univerziteta prema delu naziva
    @Query("SELECT k FROM Univerzitet u WHERE u.naziv LIKE %:keyword% ")
    List<Univerzitet> pretraziPoNazivu(String keyword);
}
