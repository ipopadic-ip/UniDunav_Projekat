package com.unidunav.predmet.service.evaluacijaZnanja;

import java.util.List;

import com.unidunav.predmet.dto.EvaluacijaZnanjaCreateDTO;
import com.unidunav.predmet.dto.EvaluacijaZnanjaDTO;

public interface EvaluacijaZnanjaService {
    EvaluacijaZnanjaDTO create(EvaluacijaZnanjaDTO dto);
    List<EvaluacijaZnanjaDTO> findAll();
    EvaluacijaZnanjaDTO findById(Long id);
    EvaluacijaZnanjaDTO update(Long id, EvaluacijaZnanjaDTO dto);
    void delete(Long id);
	void kreirajEvaluacijeZaPredmet(EvaluacijaZnanjaCreateDTO dto);
}
