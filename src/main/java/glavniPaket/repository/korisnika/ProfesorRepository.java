package glavniPaket.repository.korisnika;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.univerzitet.Univerzitet;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    Optional<Profesor> findByKorisnikId(Long korisnikId);

    List<Profesor> findByTitulaContainingIgnoreCase(String titula);

    List<Profesor> findByBiografijaContainingIgnoreCase(String tekst);

    boolean existsByKorisnikId(Long korisnikId);
}