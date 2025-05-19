package glavniPaket.repository.predmet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.predmet.TipEvaluacije;

@Repository
public interface TipEvaluacijeRepository extends JpaRepository<TipEvaluacije, Long> {

    // Opcionalno: ako želiš da tražiš po nazivu tipa
    TipEvaluacije findByTip(String tip);
}
