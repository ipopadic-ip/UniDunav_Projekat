package com.unidunav.sluzbenik.repository;

import com.unidunav.sluzbenik.model.Potvrda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PotvrdaRepository extends JpaRepository<Potvrda, Long> {

    List<Potvrda> findByStudentId(Long studentId);
}
