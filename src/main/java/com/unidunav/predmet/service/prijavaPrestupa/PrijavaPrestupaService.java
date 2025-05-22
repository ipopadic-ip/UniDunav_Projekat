package com.unidunav.predmet.service.prijavaPrestupa;

import java.util.List;

import com.unidunav.predmet.dto.PrijavaPrestupaDTO;

public interface PrijavaPrestupaService {
    PrijavaPrestupaDTO create(PrijavaPrestupaDTO dto);
    List<PrijavaPrestupaDTO> findAll();
    PrijavaPrestupaDTO findById(Long id);
    PrijavaPrestupaDTO update(Long id, PrijavaPrestupaDTO dto);
    void delete(Long id);
}
