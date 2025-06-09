package com.unidunav.sluzbenik.repository;

import com.unidunav.sluzbenik.model.Knjiga;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {
	List<Knjiga> findByNazivContainingIgnoreCase(String naziv);
}
