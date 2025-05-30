package com.unidunav.predmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unidunav.predmet.model.PohadjanjePredmeta;
@Repository
public interface PohadjanjePredmetaRepository extends JpaRepository<PohadjanjePredmeta, Long> {
}
