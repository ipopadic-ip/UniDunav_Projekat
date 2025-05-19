package glavniPaket.repository.departman;

import java.util.List;
import java.util.Optional;
import glavniPaket.model.departman.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmanRepository extends JpaRepository<Departman, Long> {

    Optional<Departman> findByNaziv(String naziv);

    List<Departman> findByNazivStartingWith(String prefix);

    boolean existsByNaziv(String naziv);

    @Query("SELECT d FROM Departman d WHERE d.naziv LIKE %:keyword%")
    List<Departman> pretraziPoNazivu(@Param("keyword") String keyword);
}