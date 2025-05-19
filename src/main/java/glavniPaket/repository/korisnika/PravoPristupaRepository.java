package glavniPaket.repository.korisnika;

import glavniPaket.model.korisnika.PravoPristupa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PravoPristupaRepository extends JpaRepository<PravoPristupa, Long> {
    Optional<PravoPristupa> findByNaziv(String naziv);
}
