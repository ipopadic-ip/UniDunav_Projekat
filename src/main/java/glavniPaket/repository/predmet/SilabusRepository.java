package glavniPaket.repository.predmet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.predmet.Silabus;

@Repository
public interface SilabusRepository extends JpaRepository<Silabus, Long> {

    Optional<Silabus> findByPredmetId(Long predmetId);

    boolean existsByPredmetId(Long predmetId);
}