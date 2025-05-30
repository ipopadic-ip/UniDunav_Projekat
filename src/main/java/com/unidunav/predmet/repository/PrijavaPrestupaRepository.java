package com.unidunav.predmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unidunav.predmet.model.PrijavaPrestupa;

@Repository
public interface PrijavaPrestupaRepository extends JpaRepository<PrijavaPrestupa, Long> {
}
