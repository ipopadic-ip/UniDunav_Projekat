package com.unidunav.predmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unidunav.predmet.model.TipEvaluacije;

@Repository
public interface TipEvaluacijeRepository extends JpaRepository<TipEvaluacije, Long> {
}
