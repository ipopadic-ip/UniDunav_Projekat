package com.unidunav.sluzbenik.service;

import com.unidunav.sluzbenik.dto.SluzbenikDTO;
import com.unidunav.sluzbenik.model.Sluzbenik;
import com.unidunav.sluzbenik.repository.SluzbenikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SluzbenikServiceImpl implements SluzbenikService {

    @Autowired
    private SluzbenikRepository repository;

    private SluzbenikDTO toDTO(Sluzbenik entity) {
        SluzbenikDTO dto = new SluzbenikDTO();
        dto.setId(entity.getId());
        dto.setIme(entity.getIme());
        dto.setPrezime(entity.getPrezime());
        return dto;
    }

    private Sluzbenik toEntity(SluzbenikDTO dto) {
        Sluzbenik entity = new Sluzbenik();
        entity.setId(dto.getId());
        entity.setIme(dto.getIme());
        entity.setPrezime(dto.getPrezime());
        return entity;
    }

    @Override
    public SluzbenikDTO create(SluzbenikDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<SluzbenikDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public SluzbenikDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public SluzbenikDTO update(Long id, SluzbenikDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setIme(dto.getIme());
            existing.setPrezime(dto.getPrezime());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
