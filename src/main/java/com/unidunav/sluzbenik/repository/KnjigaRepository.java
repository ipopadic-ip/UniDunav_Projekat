package com.unidunav.sluzbenik.repository;

import com.unidunav.sluzbenik.model.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {}
