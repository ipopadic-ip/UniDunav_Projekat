package glavniPaket.repository.predmet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.predmet.PrijavaIspita;

import java.util.List;

@Repository
public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita, Long> {

    // Sve prijave ispita za određeno pohadjanje predmeta
    List<PrijavaIspita> findByPohadjanjeId(Long pohadjanjeId);

    // Prijave po statusu (npr. položen/nepoložen)
    List<PrijavaIspita> findByStatus(boolean status);
}