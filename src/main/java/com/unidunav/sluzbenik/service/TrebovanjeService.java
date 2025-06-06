package com.unidunav.sluzbenik.service;

import com.unidunav.sluzbenik.model.Trebovanje;
import com.unidunav.sluzbenik.dto.TrebovanjeDTO;
import com.unidunav.sluzbenik.repository.TrebovanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrebovanjeService {

    @Autowired
    private TrebovanjeRepository trebovanjeRepository;

    public TrebovanjeDTO create(TrebovanjeDTO dto) {
        Trebovanje t = new Trebovanje(dto.getNazivStavke(), dto.getKolicina(), dto.getDatum());
        Trebovanje saved = trebovanjeRepository.save(t);
        return mapToDTO(saved);
    }

    public List<TrebovanjeDTO> getAll() {
        return trebovanjeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private TrebovanjeDTO mapToDTO(Trebovanje t) {
        return new TrebovanjeDTO(t.getId(), t.getNazivStavke(), t.getKolicina(), t.getDatum());
    }
}
