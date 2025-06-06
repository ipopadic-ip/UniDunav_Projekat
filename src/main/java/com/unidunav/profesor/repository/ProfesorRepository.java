package com.unidunav.profesor.repository;

import com.unidunav.profesor.model.Profesor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
	
	@Query("SELECT p FROM Profesor p WHERE p.user.email = :email")
    Optional<Profesor> findByUserEmail(@Param("email") String email);
}
