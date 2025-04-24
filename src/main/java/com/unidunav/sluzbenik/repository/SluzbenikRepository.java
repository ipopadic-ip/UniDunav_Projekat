package com.unidunav.sluzbenik.repository;

import com.unidunav.sluzbenik.model.Sluzbenik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SluzbenikRepository extends JpaRepository<Sluzbenik, Long> {
}
