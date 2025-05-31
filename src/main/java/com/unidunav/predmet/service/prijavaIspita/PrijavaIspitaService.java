package com.unidunav.predmet.service.prijavaIspita;

import java.util.List;

import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.dto.PrijavaIspitaDTO;

public interface PrijavaIspitaService {
    PrijavaIspitaDTO create(PrijavaIspitaDTO dto);
    List<PrijavaIspitaDTO> findAll();
    PrijavaIspitaDTO findById(Long id);
    PrijavaIspitaDTO update(Long id, PrijavaIspitaDTO dto);
    void delete(Long id);
    
   
    void prijavi(PrijavaIspitaDTO dto);
    List<PredmetDTO> getPredmetiZaPrijavu(Long studentId);
}
