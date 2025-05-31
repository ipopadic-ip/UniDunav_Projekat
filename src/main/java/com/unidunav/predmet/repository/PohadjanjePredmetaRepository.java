package com.unidunav.predmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unidunav.predmet.model.PohadjanjePredmeta;

public interface PohadjanjePredmetaRepository extends JpaRepository<PohadjanjePredmeta, Long> {

	@Query("SELECT pp FROM PohadjanjePredmeta pp JOIN FETCH pp.predmet WHERE pp.student.id = :studentId")
	List<PohadjanjePredmeta> findByStudentId(@Param("studentId") Long studentId);
	
	
	
}
