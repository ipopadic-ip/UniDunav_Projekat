package com.unidunav.profesorPredmet.repository;

import com.unidunav.profesorPredmet.model.TerminNastave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminNastaveRepository extends JpaRepository<TerminNastave, Long> {
    List<TerminNastave> findByProfesorPredmetId(Long profesorPredmetId);
    List<TerminNastave> findByProfesorPredmetProfesorId(Long profesorId);
}
