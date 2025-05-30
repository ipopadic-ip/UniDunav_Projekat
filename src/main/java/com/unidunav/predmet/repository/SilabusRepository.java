package com.unidunav.predmet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unidunav.predmet.model.Silabus;

@Repository
public interface SilabusRepository extends JpaRepository<Silabus, Long> {
	Optional<Silabus> findByPredmetId(Long predmetId);

}
