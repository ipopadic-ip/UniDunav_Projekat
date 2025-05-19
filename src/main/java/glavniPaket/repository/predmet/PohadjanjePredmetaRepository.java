package glavniPaket.repository.predmet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import glavniPaket.model.predmet.PohadjanjePredmeta;

public interface PohadjanjePredmetaRepository extends JpaRepository<PohadjanjePredmeta, Long> {
    List<PohadjanjePredmeta> findByStudentId(Long studentId);
    List<PohadjanjePredmeta> findByPredmetId(Long predmetId);
}
