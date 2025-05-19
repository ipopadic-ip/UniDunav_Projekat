package glavniPaket.repository.adresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.adresa.Drzava;
@Repository
public interface DrzavaRepository extends JpaRepository<Drzava, Long>{

}
