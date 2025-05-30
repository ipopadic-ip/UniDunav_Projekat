package com.unidunav.student.service;

import com.unidunav.student.dto.StudentDTO;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface StudentService {

    StudentDTO create(StudentDTO dto);
    List<StudentDTO> findAll();
    StudentDTO findById(Long id);
    StudentDTO update(Long id, StudentDTO dto);
    void delete(Long id);
    
    String uploadSlika(Long studentId, MultipartFile slika) throws IOException;

}
