package com.unidunav.profesorPredmet.service;

import com.unidunav.profesorPredmet.dto.IshodDTO;
import com.unidunav.profesorPredmet.dto.IshodResponseDTO;
import com.unidunav.profesorPredmet.model.Ishod;
import com.unidunav.profesorPredmet.repository.IshodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IshodService {

    private final IshodRepository repository;

    public IshodService(IshodRepository repository) {
        this.repository = repository;
    }

    public IshodResponseDTO create(IshodDTO dto) {
        Ishod ishod = new Ishod(dto.getTema());
        Ishod saved = repository.save(ishod);
        return new IshodResponseDTO(saved.getId(), saved.getTema());
    }

    public IshodResponseDTO update(Long id, IshodDTO dto) {
        Ishod ishod = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ishod nije pronađen"));
        ishod.setTema(dto.getTema());
        Ishod updated = repository.save(ishod);
        return new IshodResponseDTO(updated.getId(), updated.getTema());
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Ishod nije pronađen");
        }
        repository.deleteById(id);
    }

    public IshodResponseDTO findById(Long id) {
        Ishod ishod = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ishod nije pronađen"));
        return new IshodResponseDTO(ishod.getId(), ishod.getTema());
    }

    public List<IshodResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(i -> new IshodResponseDTO(i.getId(), i.getTema()))
                .collect(Collectors.toList());
    }
}
