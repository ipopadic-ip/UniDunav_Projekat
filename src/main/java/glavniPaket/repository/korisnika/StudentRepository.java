package glavniPaket.repository.korisnika;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.korisnika.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Pronalazak po korisničkom ID-ju (RegistrovaniKorisnik)
    Optional<Student> findByKorisnikId(Long korisnikId);

    // Pronalazak po broju indeksa
    Optional<Student> findByBrojIndeksa(String brojIndeksa);

    // Provera da li student postoji po broju indeksa
    boolean existsByBrojIndeksa(String brojIndeksa);

    // Pronalazak svih studenata po godini upisa
    List<Student> findByGodinaUpisa(LocalDate godinaUpisa);

    // Pronalazak studenata po ID-ju adrese
    List<Student> findByAdresaId(Long adresaId);

    // Pronalazak studenata koji imaju više od određenog broja ECTS poena
    List<Student> findByUkupanBrojECTSGreaterThan(int brojEcts);

}