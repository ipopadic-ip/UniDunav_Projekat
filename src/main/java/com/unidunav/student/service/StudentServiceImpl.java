package com.unidunav.student.service;

import com.unidunav.student.dto.StudentDTO;
import com.unidunav.student.model.Student;
import com.unidunav.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    private StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
//        dto.setIme(student.getIme());
//        dto.setPrezime(student.getPrezime());
        dto.setBrojIndeksa(student.getBrojIndeksa());
        dto.setGodinaUpisa(student.getGodinaUpisa());
        dto.setProsecnaOcena(student.getProsecnaOcena());
        dto.setUkupnoEcts(student.getUkupnoEcts());
        return dto;
    }

    private Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
//        student.setIme(dto.getIme());
//        student.setPrezime(dto.getPrezime());
        student.setBrojIndeksa(dto.getBrojIndeksa());
        student.setGodinaUpisa(dto.getGodinaUpisa());
        student.setProsecnaOcena(dto.getProsecnaOcena());
        student.setUkupnoEcts(dto.getUkupnoEcts());
        return student;
    }

    @Override
    public StudentDTO create(StudentDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<StudentDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public StudentDTO update(Long id, StudentDTO dto) {
        return repository.findById(id).map(existing -> {
//            existing.setIme(dto.getIme());
//            existing.setPrezime(dto.getPrezime());
            existing.setBrojIndeksa(dto.getBrojIndeksa());
            existing.setGodinaUpisa(dto.getGodinaUpisa());
            existing.setProsecnaOcena(dto.getProsecnaOcena());
            existing.setUkupnoEcts(dto.getUkupnoEcts());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
