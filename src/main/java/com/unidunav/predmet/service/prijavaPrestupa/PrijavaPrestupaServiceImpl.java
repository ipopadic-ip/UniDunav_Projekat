package com.unidunav.predmet.service.prijavaPrestupa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidunav.predmet.dto.PrijavaPrestupaDTO;
import com.unidunav.predmet.model.PohadjanjePredmeta;
import com.unidunav.predmet.model.PrijavaPrestupa;
import com.unidunav.predmet.repository.PohadjanjePredmetaRepository;
import com.unidunav.predmet.repository.PrijavaPrestupaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrijavaPrestupaServiceImpl implements PrijavaPrestupaService {

    @Autowired
    private PrijavaPrestupaRepository repository;

    @Autowired
    private PohadjanjePredmetaRepository pohadjanjeRepo;

    private PrijavaPrestupaDTO toDTO(PrijavaPrestupa prijava) {
        PrijavaPrestupaDTO dto = new PrijavaPrestupaDTO();
        dto.setId(prijava.getId());
        dto.setOpis(prijava.getOpis());
        dto.setDatum(prijava.getDatum());
        dto.setPohadjanjeId(prijava.getPohadjanje().getId());
        return dto;
    }

    private PrijavaPrestupa toEntity(PrijavaPrestupaDTO dto) {
        PohadjanjePredmeta pohadjanje = pohadjanjeRepo.findById(dto.getPohadjanjeId()).orElseThrow();
        return new PrijavaPrestupa(dto.getId(), dto.getOpis(), dto.getDatum(), pohadjanje);
    }

    @Override
    public PrijavaPrestupaDTO create(PrijavaPrestupaDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<PrijavaPrestupaDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PrijavaPrestupaDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public PrijavaPrestupaDTO update(Long id, PrijavaPrestupaDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setOpis(dto.getOpis());
            existing.setDatum(dto.getDatum());
            existing.setPohadjanje(pohadjanjeRepo.findById(dto.getPohadjanjeId()).orElseThrow());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
