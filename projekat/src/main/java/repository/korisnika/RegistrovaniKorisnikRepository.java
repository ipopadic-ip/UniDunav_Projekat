package repository.korisnika;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import model.korisnika.RegistrovaniKorisnik;

public interface RegistrovaniKorisnikRepository extends JpaRepository<RegistrovaniKorisnik, Integer> {
	  // Pronalazi korisnika po korisničkom imenu
    Optional<RegistrovaniKorisnik> findByKorisnickoIme(String korisnickoIme);

    // Pronalazi sve korisnike sa određenim imenom i prezimenom
    List<RegistrovaniKorisnik> findByImeAndPrezime(String ime, String prezime);

    // Pronalazi korisnike čije ime počinje određenim slovima
    List<RegistrovaniKorisnik> findByImeStartingWith(String prefix);

    // Provera da li korisnik postoji na osnovu email-a
    boolean existsByEmail(String email);

    // Custom JPQL upit za pronalazak korisnika prema delu imena ili prezimena
    @Query("SELECT k FROM RegistrovaniKorisnik k WHERE k.ime LIKE %:keyword% OR k.prezime LIKE %:keyword%")
    List<RegistrovaniKorisnik> pretraziPoImenuIliPrezimenu(String keyword);
}
