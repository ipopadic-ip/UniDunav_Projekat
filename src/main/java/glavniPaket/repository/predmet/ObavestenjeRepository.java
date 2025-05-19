package glavniPaket.repository.predmet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.predmet.Obavestenje;

@Repository
public interface ObavestenjeRepository extends JpaRepository<Obavestenje, Long> {

    // Pronađi sva obaveštenja vezana za određeni predmet
    List<Obavestenje> findByPredmetId(Long predmetId);

    // Pretraga obaveštenja po tekstu (delimično poklapanje)
    List<Obavestenje> findByTekstContainingIgnoreCase(String keyword);
}
