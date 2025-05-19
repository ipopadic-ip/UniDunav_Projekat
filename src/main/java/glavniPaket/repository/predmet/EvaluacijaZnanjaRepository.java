package glavniPaket.repository.predmet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.predmet.EvaluacijaZnanja;

@Repository
public interface EvaluacijaZnanjaRepository extends JpaRepository<EvaluacijaZnanja, Long> {

    List<EvaluacijaZnanja> findByPohadjanjeId(Long pohadjanjeId);

    List<EvaluacijaZnanja> findByTipEvaluacijeId(Long tipEvaluacijeId);
}
