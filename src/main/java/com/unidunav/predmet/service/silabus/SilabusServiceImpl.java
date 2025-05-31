package com.unidunav.predmet.service.silabus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidunav.predmet.dto.SilabusDTO;
import com.unidunav.predmet.model.Predmet;
import com.unidunav.predmet.model.Silabus;
import com.unidunav.predmet.repository.PredmetRepository;
import com.unidunav.predmet.repository.SilabusRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SilabusServiceImpl implements SilabusService {

    @Autowired
    private SilabusRepository silabusRepository;

    @Autowired
    private PredmetRepository predmetRepository;

    private SilabusDTO toDTO(Silabus silabus) {
        SilabusDTO dto = new SilabusDTO();
        dto.setId(silabus.getId());
        dto.setSadrzaj(silabus.getSadrzaj());
        dto.setPredmetId(silabus.getPredmet().getId());
        return dto;
    }

    private Silabus toEntity(SilabusDTO dto) {
        Silabus entity = new Silabus();
        entity.setId(dto.getId());
        entity.setSadrzaj(dto.getSadrzaj());
        Predmet predmet = predmetRepository.findById(dto.getPredmetId()).orElseThrow();
        entity.setPredmet(predmet);
        return entity;
    }

    @Override
    public SilabusDTO create(SilabusDTO dto) {
        return toDTO(silabusRepository.save(toEntity(dto)));
    }

    @Override
    public List<SilabusDTO> findAll() {
        return silabusRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public SilabusDTO findById(Long id) {
        return silabusRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public SilabusDTO update(Long id, SilabusDTO dto) {
        return silabusRepository.findById(id).map(existing -> {
            existing.setSadrzaj(dto.getSadrzaj());
            Predmet predmet = predmetRepository.findById(dto.getPredmetId()).orElseThrow();
            existing.setPredmet(predmet);
            return toDTO(silabusRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        silabusRepository.deleteById(id);
    }
}
