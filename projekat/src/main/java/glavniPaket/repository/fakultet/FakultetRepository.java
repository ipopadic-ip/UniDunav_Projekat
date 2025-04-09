package glavniPaket.repository.fakultet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.univerzitet.Univerzitet;

public interface FakultetRepository extends JpaRepository<Fakultet, Integer>{
	// Pronalazi univerzitet po nazivu
    Optional<Fakultet> findByNaziv(String naziv);

    // Pronalazi univerzitet čiji naziv počinje određenim slovima
    List<Fakultet> findByNazivStartingWith(String prefix);

    // Provera da li univerzitet postoji na osnovu email-a
    boolean existsByEmail(String email);

    // Custom JPQL upit za pronalazak univerziteta prema delu naziva
    @Query("SELECT f FROM Fakultet f WHERE f.naziv LIKE %:keyword% ")
    List<Univerzitet> pretraziPoNazivu(String keyword);
}
