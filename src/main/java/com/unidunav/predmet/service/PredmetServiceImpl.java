package com.unidunav.predmet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidunav.godinaStudija.model.GodinaStudija;
import com.unidunav.godinaStudija.repository.GodinaStudijaRepository;
import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.model.Predmet;
import com.unidunav.predmet.repository.PredmetRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PredmetServiceImpl implements PredmetService {

    @Autowired
    private PredmetRepository predmetRepository;

    @Autowired
    private GodinaStudijaRepository godinaStudijaRepository;

    private PredmetDTO toDTO(Predmet p) {
        PredmetDTO dto = new PredmetDTO();
        dto.setId(p.getId());
        dto.setNaziv(p.getNaziv());
        dto.setEcts(p.getEcts());
        dto.setInformacijeOPredmetu(p.getInformacijeOPredmetu());
        dto.setGodinaStudijaId(p.getGodinaStudija().getId());
        return dto;
    }

    private Predmet toEntity(PredmetDTO dto) {
        Predmet p = new Predmet();
        p.setId(dto.getId());
        p.setNaziv(dto.getNaziv());
        p.setEcts(dto.getEcts());
        p.setInformacijeOPredmetu(dto.getInformacijeOPredmetu());
        GodinaStudija gs = godinaStudijaRepository.findById(dto.getGodinaStudijaId()).orElseThrow();
        p.setGodinaStudija(gs);
        return p;
    }

    @Override
    public PredmetDTO create(PredmetDTO dto) {
        return toDTO(predmetRepository.save(toEntity(dto)));
    }

    @Override
    public List<PredmetDTO> findAll() {
        return predmetRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PredmetDTO findById(Long id) {
        return predmetRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public PredmetDTO update(Long id, PredmetDTO dto) {
        return predmetRepository.findById(id).map(existing -> {
            existing.setNaziv(dto.getNaziv());
            existing.setEcts(dto.getEcts());
            existing.setInformacijeOPredmetu(dto.getInformacijeOPredmetu());
            GodinaStudija gs = godinaStudijaRepository.findById(dto.getGodinaStudijaId()).orElseThrow();
            existing.setGodinaStudija(gs);
            return toDTO(predmetRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        predmetRepository.deleteById(id);
    }
}
