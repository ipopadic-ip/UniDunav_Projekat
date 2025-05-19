package glavniPaket.repository.adresa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import glavniPaket.model.adresa.Adresa;
@Repository
public interface AdresaRepository extends JpaRepository<Adresa, Long> {

    // Pronađi sve adrese u nekom mestu po ID-ju mesta
    List<Adresa> findByMestoId(Long mestoId);

    // Opcionalno: pronađi adrese po ulici
    List<Adresa> findByUlicaContainingIgnoreCase(String ulica);
}