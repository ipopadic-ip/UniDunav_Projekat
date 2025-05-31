package com.unidunav.predmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidunav.predmet.model.Predmet;

public interface PredmetRepository extends JpaRepository<Predmet, Long> {

	List<Predmet> findByGodinaStudijaId(Long id);
}
