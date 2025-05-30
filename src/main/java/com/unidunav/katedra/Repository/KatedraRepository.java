package com.unidunav.katedra.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidunav.katedra.model.Katedra;

public interface KatedraRepository extends JpaRepository<Katedra, Long> {
	List<Katedra> findByDepartmanId(Long departmanId);
}
