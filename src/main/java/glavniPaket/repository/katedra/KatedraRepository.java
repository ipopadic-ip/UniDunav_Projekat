package glavniPaket.repository.katedra;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;

@Repository
public interface KatedraRepository extends JpaRepository<Katedra, Long> {

    Optional<Katedra> findByNaziv(String naziv);

    List<Katedra> findByNazivStartingWith(String prefix);

    @Query("SELECT k FROM Katedra k WHERE k.naziv LIKE %:keyword%")
    List<Katedra> pretraziPoNazivu(@Param("keyword") String keyword);

}