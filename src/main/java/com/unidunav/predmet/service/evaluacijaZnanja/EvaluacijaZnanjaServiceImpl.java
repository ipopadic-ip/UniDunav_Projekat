package com.unidunav.predmet.service.evaluacijaZnanja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidunav.predmet.dto.EvaluacijaZnanjaDTO;
import com.unidunav.predmet.model.EvaluacijaZnanja;
import com.unidunav.predmet.model.PohadjanjePredmeta;
import com.unidunav.predmet.model.TipEvaluacije;
import com.unidunav.predmet.repository.EvaluacijaZnanjaRepository;
import com.unidunav.predmet.repository.PohadjanjePredmetaRepository;
import com.unidunav.predmet.repository.TipEvaluacijeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluacijaZnanjaServiceImpl implements EvaluacijaZnanjaService {

    @Autowired private EvaluacijaZnanjaRepository repository;
    @Autowired private TipEvaluacijeRepository tipRepo;
    @Autowired private PohadjanjePredmetaRepository pohRepo;

    private EvaluacijaZnanjaDTO toDTO(EvaluacijaZnanja ez) {
        EvaluacijaZnanjaDTO dto = new EvaluacijaZnanjaDTO();
        dto.setId(ez.getId());
        dto.setVremePocetka(ez.getVremePocetka());
        dto.setBrojBodova(ez.getBrojBodova());
        dto.setTipEvaluacijeId(ez.getTipEvaluacije().getId());
        dto.setPohadjanjeId(ez.getPohadjanje().getId());
        return dto;
    }

    private EvaluacijaZnanja toEntity(EvaluacijaZnanjaDTO dto) {
        TipEvaluacije tip = tipRepo.findById(dto.getTipEvaluacijeId()).orElseThrow();
        PohadjanjePredmeta poh = pohRepo.findById(dto.getPohadjanjeId()).orElseThrow();

        EvaluacijaZnanja ez = new EvaluacijaZnanja();
        ez.setId(dto.getId());
        ez.setVremePocetka(dto.getVremePocetka());
        ez.setBrojBodova(dto.getBrojBodova());
        ez.setTipEvaluacije(tip);
        ez.setPohadjanje(poh);
        return ez;
    }

    @Override
    public EvaluacijaZnanjaDTO create(EvaluacijaZnanjaDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<EvaluacijaZnanjaDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public EvaluacijaZnanjaDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public EvaluacijaZnanjaDTO update(Long id, EvaluacijaZnanjaDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setVremePocetka(dto.getVremePocetka());
            existing.setBrojBodova(dto.getBrojBodova());
            existing.setTipEvaluacije(tipRepo.findById(dto.getTipEvaluacijeId()).orElseThrow());
            existing.setPohadjanje(pohRepo.findById(dto.getPohadjanjeId()).orElseThrow());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
