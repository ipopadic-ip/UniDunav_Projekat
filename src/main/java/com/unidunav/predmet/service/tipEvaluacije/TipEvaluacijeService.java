package com.unidunav.predmet.service.tipEvaluacije;

import java.util.List;

import com.unidunav.predmet.dto.TipEvaluacijeDTO;

public interface TipEvaluacijeService {

    TipEvaluacijeDTO create(TipEvaluacijeDTO dto);
    List<TipEvaluacijeDTO> findAll();
    TipEvaluacijeDTO findById(Long id);
    TipEvaluacijeDTO update(Long id, TipEvaluacijeDTO dto);
    void delete(Long id);
}
