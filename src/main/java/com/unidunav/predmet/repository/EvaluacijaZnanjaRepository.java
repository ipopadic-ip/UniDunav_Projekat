package com.unidunav.predmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unidunav.predmet.model.EvaluacijaZnanja;
@Repository
public interface EvaluacijaZnanjaRepository extends JpaRepository<EvaluacijaZnanja, Long> {
}
