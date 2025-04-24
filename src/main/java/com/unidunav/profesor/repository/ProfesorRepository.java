package com.unidunav.profesor.repository;

import com.unidunav.profesor.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}
