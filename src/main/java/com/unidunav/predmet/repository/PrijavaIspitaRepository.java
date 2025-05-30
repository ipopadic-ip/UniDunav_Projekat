package com.unidunav.predmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unidunav.predmet.model.PrijavaIspita;
@Repository
public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita, Long> {
}
