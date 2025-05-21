package com.unidunav.profesor.service;

import com.unidunav.profesor.dto.ProfesorDTO;
import com.unidunav.profesor.model.Profesor;
import com.unidunav.profesor.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository repository;

    public ProfesorDTO toDTO(Profesor profesor) {
        ProfesorDTO dto = new ProfesorDTO();
        dto.setId(profesor.getId());
        dto.setIme(profesor.getIme());
        dto.setPrezime(profesor.getPrezime());
        dto.setBiografija(profesor.getBiografija());
        return dto;
    }

    public Profesor toEntity(ProfesorDTO dto) {
        Profesor profesor = new Profesor();
        profesor.setId(dto.getId());
        profesor.setIme(dto.getIme());
        profesor.setPrezime(dto.getPrezime());
        profesor.setBiografija(dto.getBiografija());
        return profesor;
    }

    @Override
    public ProfesorDTO create(ProfesorDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<ProfesorDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProfesorDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public ProfesorDTO update(Long id, ProfesorDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setIme(dto.getIme());
            existing.setPrezime(dto.getPrezime());
            existing.setBiografija(dto.getBiografija());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
