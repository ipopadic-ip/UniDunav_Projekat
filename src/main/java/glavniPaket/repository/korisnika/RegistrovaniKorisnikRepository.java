package glavniPaket.repository.korisnika;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.korisnika.RegistrovaniKorisnik;

@Repository
public interface RegistrovaniKorisnikRepository extends JpaRepository<RegistrovaniKorisnik, Long> {
	  // Pronalazi korisnika po korisničkom imenu
    Optional<RegistrovaniKorisnik> findByKorisnickoIme(String korisnickoIme);

    // Pronalazi sve korisnike sa određenim imenom i prezimenom
    List<RegistrovaniKorisnik> findByImeAndPrezime(String ime, String prezime);

    // Pronalazi korisnike čije ime počinje određenim slovima
    List<RegistrovaniKorisnik> findByImeStartingWith(String prefix);

    
    Optional<RegistrovaniKorisnik> findByEmail(String email);

   

    boolean existsByJmbg(String jmbg);

    boolean existsByEmail(String email);
    

    // Custom JPQL upit za pronalazak korisnika prema delu imena ili prezimena
    @Query("SELECT k FROM RegistrovaniKorisnik k WHERE k.ime LIKE %:keyword% OR k.prezime LIKE %:keyword%")
    List<RegistrovaniKorisnik> pretraziPoImenuIliPrezimenu(String keyword);
}
