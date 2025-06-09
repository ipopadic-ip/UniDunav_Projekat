package com.unidunav.predmet.service.pohadjanjePredmeta;

import java.util.List;

import com.unidunav.predmet.dto.PohadjanjePredmetaDTO;

public interface PohadjanjePredmetaService {
    PohadjanjePredmetaDTO create(PohadjanjePredmetaDTO dto);
    List<PohadjanjePredmetaDTO> findAll();
    PohadjanjePredmetaDTO findById(Long id);
    PohadjanjePredmetaDTO update(Long id, PohadjanjePredmetaDTO dto);
    void delete(Long id);
    void upisiStudenta(Long studentId, List<Long> predmetIds);
}
