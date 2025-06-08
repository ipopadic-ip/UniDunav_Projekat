package com.unidunav.predmet.service.prijavaIspita;

import java.time.LocalDateTime;
import java.util.List;

import com.unidunav.predmet.dto.PohadjanjePredmetaDTO;
import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.dto.PrijavaIspitaDTO;
import com.unidunav.predmet.model.PohadjanjePredmeta;

public interface PrijavaIspitaService {
    PrijavaIspitaDTO create(PrijavaIspitaDTO dto);
    List<PrijavaIspitaDTO> findAll();
    PrijavaIspitaDTO findById(Long id);
    PrijavaIspitaDTO update(Long id, PrijavaIspitaDTO dto);
    void delete(Long id);
    
   
    
    List<PrijavaIspitaDTO> getDostupnePrijave(Long studentId);
    PrijavaIspitaDTO prijaviIspit(Long prijavaId);
	void kreirajPrijaveZaPredmet(Long predmetId, LocalDateTime datumIspita);
	

}
