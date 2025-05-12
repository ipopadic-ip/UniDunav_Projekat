package glavniPaket.repository.korisnika;
import glavniPaket.model.korisnika.DodeljenoPravoPristupa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DodeljenoPravoPristupaRepository extends JpaRepository<DodeljenoPravoPristupa, Integer> {
	List<DodeljenoPravoPristupa> findByKorisnikId(Integer korisnikId);

}
