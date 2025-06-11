package com.unidunav.dokument.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidunav.dokument.model.Dokument;

public interface DokumentRepository extends JpaRepository<Dokument, Long> {
	 // Vidljivo korisnicima (samo aktivni)
    List<Dokument> findByDeletedFalse();

    // Za admina (svi dokumenti)
    List<Dokument> findAll();

    Optional<Dokument> findByIdAndDeletedFalse(Long id);
}

