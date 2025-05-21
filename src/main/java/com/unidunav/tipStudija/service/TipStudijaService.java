package com.unidunav.tipStudija.service;

import java.util.List;

import com.unidunav.tipStudija.dto.TipStudijaDTO;

public interface TipStudijaService {
	TipStudijaDTO create(TipStudijaDTO dto);
	List<TipStudijaDTO> findAll();
	TipStudijaDTO findById(Long id);
	TipStudijaDTO update(Long id, TipStudijaDTO dto);
	void delete(Long id);
}
