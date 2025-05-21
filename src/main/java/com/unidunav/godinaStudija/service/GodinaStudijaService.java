package com.unidunav.godinaStudija.service;

import java.util.List;

import com.unidunav.godinaStudija.dto.GodinaStudijaDTO;

public interface GodinaStudijaService {
	GodinaStudijaDTO create(GodinaStudijaDTO dto);
	List<GodinaStudijaDTO> findAll();
	GodinaStudijaDTO findById(Long id);
	GodinaStudijaDTO update(Long id, GodinaStudijaDTO dto);
	void delete(Long id);
}
