package com.unidunav.predmet.service.prijavaIspita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidunav.predmet.dto.PrijavaIspitaDTO;
import com.unidunav.predmet.model.PohadjanjePredmeta;
import com.unidunav.predmet.model.PrijavaIspita;
import com.unidunav.predmet.repository.PohadjanjePredmetaRepository;
import com.unidunav.predmet.repository.PrijavaIspitaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrijavaIspitaServiceImpl implements PrijavaIspitaService {

    @Autowired
    private PrijavaIspitaRepository prijavaRepo;

    @Autowired
    private PohadjanjePredmetaRepository pohadjanjeRepo;

    private PrijavaIspitaDTO toDTO(PrijavaIspita entitet) {
        PrijavaIspitaDTO dto = new PrijavaIspitaDTO();
        dto.setId(entitet.getId());
        dto.setDatumPrijave(entitet.getDatumPrijave());
        dto.setDatumIspita(entitet.getDatumIspita());
        dto.setStatus(entitet.isStatus());
        dto.setPohadjanjeId(entitet.getPohadjanje().getId());
        return dto;
    }

    private PrijavaIspita toEntity(PrijavaIspitaDTO dto) {
        PrijavaIspita entitet = new PrijavaIspita();
        entitet.setId(dto.getId());
        entitet.setDatumPrijave(dto.getDatumPrijave());
        entitet.setDatumIspita(dto.getDatumIspita());
        entitet.setStatus(dto.isStatus());
        PohadjanjePredmeta pohadjanje = pohadjanjeRepo.findById(dto.getPohadjanjeId()).orElseThrow();
        entitet.setPohadjanje(pohadjanje);
        return entitet;
    }

    @Override
    public PrijavaIspitaDTO create(PrijavaIspitaDTO dto) {
        return toDTO(prijavaRepo.save(toEntity(dto)));
    }

    @Override
    public List<PrijavaIspitaDTO> findAll() {
        return prijavaRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PrijavaIspitaDTO findById(Long id) {
        return prijavaRepo.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public PrijavaIspitaDTO update(Long id, PrijavaIspitaDTO dto) {
        return prijavaRepo.findById(id).map(existing -> {
            existing.setDatumPrijave(dto.getDatumPrijave());
            existing.setDatumIspita(dto.getDatumIspita());
            existing.setStatus(dto.isStatus());
            existing.setPohadjanje(pohadjanjeRepo.findById(dto.getPohadjanjeId()).orElseThrow());
            return toDTO(prijavaRepo.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        prijavaRepo.deleteById(id);
    }
}
