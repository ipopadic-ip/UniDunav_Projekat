package com.unidunav.student.repository;

import com.unidunav.student.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByBrojIndeksaContainingIgnoreCase(String indeks);
}
