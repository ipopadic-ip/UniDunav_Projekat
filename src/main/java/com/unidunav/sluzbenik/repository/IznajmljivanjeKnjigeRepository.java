package com.unidunav.sluzbenik.repository;

import com.unidunav.sluzbenik.model.IznajmljivanjeKnjige;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IznajmljivanjeKnjigeRepository extends JpaRepository<IznajmljivanjeKnjige, Long> {
	List<IznajmljivanjeKnjige> findByVracenoFalse();
	
	boolean existsByPrimerakKnjigeIdAndKorisnikIdAndVracenoIsNull(Long primerakId, Long korisnikId);

    List<IznajmljivanjeKnjige> findByKorisnikIdAndVracenoIsNull(Long korisnikId);

}
