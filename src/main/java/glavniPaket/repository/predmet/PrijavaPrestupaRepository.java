package glavniPaket.repository.predmet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.predmet.PrijavaPrestupa;

@Repository
public interface PrijavaPrestupaRepository extends JpaRepository<PrijavaPrestupa, Long> {

    // Vrati sve prijave za određeno pohađanje predmeta
    List<PrijavaPrestupa> findByPohadjanjeId(Long pohadjanjeId);
}
