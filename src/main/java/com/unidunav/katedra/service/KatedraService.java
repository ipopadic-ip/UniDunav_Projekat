package com.unidunav.katedra.service;

import java.util.List;

import com.unidunav.katedra.dto.KatedraDTO;

public interface KatedraService {
	KatedraDTO create(KatedraDTO dto);
	List<KatedraDTO> findAll();
	KatedraDTO findById(Long id);
	KatedraDTO update(Long id, KatedraDTO dto);
	void delete(Long id);
}
