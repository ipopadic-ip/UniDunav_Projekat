package glavniPaket.repository.fakultet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.univerzitet.Univerzitet;

public interface FakultetRepository extends JpaRepository<Fakultet, Long> {

    Optional<Fakultet> findByNaziv(String naziv);

    List<Fakultet> findByNazivStartingWith(String prefix);

    boolean existsByEmail(String email);

    @Query("SELECT f FROM Fakultet f WHERE f.naziv LIKE %:keyword%")
    List<Fakultet> pretraziPoNazivu(@Param("keyword") String keyword);

}
