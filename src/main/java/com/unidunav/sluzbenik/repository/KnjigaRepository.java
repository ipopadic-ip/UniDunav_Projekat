package com.unidunav.sluzbenik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidunav.sluzbenik.model.Knjiga;

import java.util.List;

public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {

    List<Knjiga> findByNazivContainingIgnoreCase(String naziv);
}
