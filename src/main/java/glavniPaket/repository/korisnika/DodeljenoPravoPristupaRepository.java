package glavniPaket.repository.korisnika;
import glavniPaket.model.korisnika.DodeljenoPravoPristupa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DodeljenoPravoPristupaRepository extends JpaRepository<DodeljenoPravoPristupa, Long> {
    List<DodeljenoPravoPristupa> findByKorisnikId(Long korisnikId);
    boolean existsByKorisnikIdAndPravoPristupa_Naziv(Long korisnikId, String naziv);
}