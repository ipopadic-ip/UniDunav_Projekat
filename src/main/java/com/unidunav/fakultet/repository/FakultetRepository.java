package com.unidunav.fakultet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unidunav.fakultet.model.Fakultet;

public interface FakultetRepository extends JpaRepository<Fakultet, Long>{
	List<Fakultet> findByDeletedFalse();

	@Query("SELECT f FROM Fakultet f ORDER BY f.deleted ASC, f.naziv ASC")
	List<Fakultet> findAllSortedByDeleted();

}
