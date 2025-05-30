package com.unidunav.predmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unidunav.predmet.model.NastavniMaterijal;

@Repository
public interface NastavniMaterijalRepository extends JpaRepository<NastavniMaterijal, Long> {
    List<NastavniMaterijal> findByPredmetId(Long predmetId);
}

