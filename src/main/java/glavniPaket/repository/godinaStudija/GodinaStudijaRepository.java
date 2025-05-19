package glavniPaket.repository.godinaStudija;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import glavniPaket.model.godinaStudija.GodinaStudija;

@Repository
public interface GodinaStudijaRepository extends JpaRepository<GodinaStudija, Long> {

    List<GodinaStudija> findByGodina(int godina);

    @Query("SELECT g FROM GodinaStudija g WHERE g.godina = :godina AND g.studijskiProgram.id = :programId")
    List<GodinaStudija> findByGodinaAndProgram(@Param("godina") int godina, @Param("programId") Long programId);
}