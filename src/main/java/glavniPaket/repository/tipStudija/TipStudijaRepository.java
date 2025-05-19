package glavniPaket.repository.tipStudija;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import glavniPaket.model.tipStudija.TipStudija;

@Repository
public interface TipStudijaRepository extends JpaRepository<TipStudija, Long> {

    Optional<TipStudija> findByTip(String tip);

    List<TipStudija> findByTipStartingWith(String prefix);

    @Query("SELECT t FROM TipStudija t WHERE t.tip LIKE %:keyword%")
    List<TipStudija> pretraziPoTipu(@Param("keyword") String keyword);
}
