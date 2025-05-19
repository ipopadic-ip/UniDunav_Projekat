package glavniPaket.repository.adresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.adresa.Mesto;

import java.util.List;

@Repository
public interface MestoRepository extends JpaRepository<Mesto, Long> {

    // Pronađi mesta po nazivu (ne mora da bude tačan naziv)
    List<Mesto> findByNazivContainingIgnoreCase(String naziv);
}
