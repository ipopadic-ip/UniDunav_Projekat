package com.unidunav.predmet.service.tipEvaluacije;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidunav.predmet.dto.TipEvaluacijeDTO;
import com.unidunav.predmet.model.TipEvaluacije;
import com.unidunav.predmet.repository.TipEvaluacijeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipEvaluacijeServiceImpl implements TipEvaluacijeService {

    @Autowired
    private TipEvaluacijeRepository repository;

    private TipEvaluacijeDTO toDTO(TipEvaluacije entity) {
        TipEvaluacijeDTO dto = new TipEvaluacijeDTO();
        dto.setId(entity.getId());
        dto.setTip(entity.getTip());
        return dto;
    }

    private TipEvaluacije toEntity(TipEvaluacijeDTO dto) {
        TipEvaluacije entity = new TipEvaluacije();
        entity.setId(dto.getId());
        entity.setTip(dto.getTip());
        return entity;
    }

    @Override
    public TipEvaluacijeDTO create(TipEvaluacijeDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<TipEvaluacijeDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public TipEvaluacijeDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public TipEvaluacijeDTO update(Long id, TipEvaluacijeDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setTip(dto.getTip());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
