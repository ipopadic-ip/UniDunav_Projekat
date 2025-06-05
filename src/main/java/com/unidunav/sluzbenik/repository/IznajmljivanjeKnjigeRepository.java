package com.unidunav.sluzbenik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidunav.sluzbenik.model.IznajmljivanjeKnjige;

public interface IznajmljivanjeKnjigeRepository extends JpaRepository<IznajmljivanjeKnjige, Long> {

    boolean existsByPrimerakKnjigeIdAndKorisnikIdAndVracenoIsNull(Long primerakId, Long korisnikId);

    List<IznajmljivanjeKnjige> findByKorisnikIdAndVracenoIsNull(Long korisnikId);
}
