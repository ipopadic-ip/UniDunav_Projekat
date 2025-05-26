package com.unidunav.dapartman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidunav.dapartman.model.Departman;

public interface DepartmanRepository extends JpaRepository<Departman, Long>{
	List<Departman> findByFakultetId(Long fakultetId);

}
