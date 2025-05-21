package com.unidunav.predmet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.model.Predmet;
import com.unidunav.predmet.repository.PredmetRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PredmetServiceImpl implements PredmetService {

    private final PredmetRepository predmetRepository;

    public PredmetServiceImpl(PredmetRepository predmetRepository) {
        this.predmetRepository = predmetRepository;
    }

    @Override
    public PredmetDTO create(PredmetDTO dto) {
        Predmet entity = toEntity(dto);
        entity = predmetRepository.save(entity);
        return toDTO(entity);
    }

    @Override
    public List<PredmetDTO> findAll() {
        return predmetRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PredmetDTO findById(Long id) {
        Predmet entity = predmetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Predmet sa ID " + id + " nije pronađen."));
        return toDTO(entity);
    }

    @Override
    public PredmetDTO update(Long id, PredmetDTO dto) {
        Predmet entity = predmetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Predmet sa ID " + id + " nije pronađen."));

        entity.setNaziv(dto.getNaziv());
        entity.setEcts(dto.getEcts());
        entity.setInformacijeOPredmetu(dto.getInformacijeOPredmetu());

        return toDTO(predmetRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!predmetRepository.existsById(id)) {
            throw new EntityNotFoundException("Predmet sa ID " + id + " ne postoji.");
        }
        predmetRepository.deleteById(id);
    }

    @Override
    public PredmetDTO toDTO(Predmet entity) {
        PredmetDTO dto = new PredmetDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setEcts(entity.getEcts());
        dto.setInformacijeOPredmetu(entity.getInformacijeOPredmetu());

        if (entity.getGodinaStudija() != null) {
            dto.setGodinaStudijaId(entity.getGodinaStudija().getId());
        }
        if (entity.getKatedra() != null) {
            dto.setKatedraId(entity.getKatedra().getId());
        }

        return dto;
    }

    @Override
    public Predmet toEntity(PredmetDTO dto) {
        Predmet entity = new Predmet();
        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        entity.setEcts(dto.getEcts());
        entity.setInformacijeOPredmetu(dto.getInformacijeOPredmetu());

        return entity;
    }
}
