package glavniPaket.repository.studijskiProgram;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import glavniPaket.model.studijskiProgram.StudijskiProgram;

@Repository
public interface StudijskiProgramRepository extends JpaRepository<StudijskiProgram, Long> {

    Optional<StudijskiProgram> findByNaziv(String naziv);

    List<StudijskiProgram> findByNazivStartingWith(String prefix);

    @Query("SELECT sp FROM StudijskiProgram sp WHERE sp.naziv LIKE %:keyword%")
    List<StudijskiProgram> pretraziPoNazivu(@Param("keyword") String keyword);
}
