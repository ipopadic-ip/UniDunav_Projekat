package glavniPaket.repository.korisnika;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import glavniPaket.model.korisnika.*;
public interface OsobljeRepository extends JpaRepository<Osoblje, Integer>{

}
