package com.unidunav.sluzbenik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidunav.sluzbenik.model.PrimerakKnjige;

import java.util.List;

public interface PrimerakKnjigeRepository extends JpaRepository<PrimerakKnjige, Long> {

    List<PrimerakKnjige> findByKnjigaIdAndDostupanTrue(Long knjigaId);

    boolean existsByKnjigaIdAndDostupanTrue(Long knjigaId);
    List<PrimerakKnjige> findByKnjigaId(Long knjigaId);
}