package com.unidunav.student.service;

import com.unidunav.student.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO create(StudentDTO dto);
    List<StudentDTO> findAll();
    StudentDTO findById(Long id);
    StudentDTO update(Long id, StudentDTO dto);
    void delete(Long id);
}
