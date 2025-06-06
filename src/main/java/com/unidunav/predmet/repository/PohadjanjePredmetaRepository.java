package com.unidunav.predmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unidunav.predmet.model.PohadjanjePredmeta;
import org.springframework.stereotype.Repository;

@Repository
public interface PohadjanjePredmetaRepository extends JpaRepository<PohadjanjePredmeta, Long> {
	@Query("SELECT pp FROM PohadjanjePredmeta pp JOIN FETCH pp.predmet WHERE pp.student.id = :studentId")
	List<PohadjanjePredmeta> findByStudentId(@Param("studentId") Long studentId);
	
	@Query("SELECT pp FROM PohadjanjePredmeta pp " +
		       "JOIN pp.predmet p " +
		       "JOIN p.profesori ppred " +
		       "WHERE ppred.profesor.id = :profesorId")
		List<PohadjanjePredmeta> findByProfesorId(@Param("profesorId") Long profesorId);


}
