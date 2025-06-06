package com.unidunav.predmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unidunav.predmet.model.PrijavaIspita;
@Repository
public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita, Long> {
	@Query("SELECT p FROM PrijavaIspita p WHERE p.status = false AND p.datumPrijave IS NULL AND p.pohadjanje.student.id = :studentId")
	List<PrijavaIspita> findNeprijavljeneZaStudenta(@Param("studentId") Long studentId);
}
