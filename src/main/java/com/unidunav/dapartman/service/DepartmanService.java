package com.unidunav.dapartman.service;

import java.util.List;

import com.unidunav.dapartman.dto.DepartmanDTO;
import com.unidunav.dapartman.model.Departman;

public interface DepartmanService {
	DepartmanDTO create(DepartmanDTO dto);
	List<DepartmanDTO> findAll();
	DepartmanDTO findById(Long id);
	DepartmanDTO update(Long id, DepartmanDTO dto);
	void delete(Long id);
	
	List<DepartmanDTO> findByFakultetId(Long fakultetId);

	
	DepartmanDTO toDTO(Departman entity);
	Departman toEntity(DepartmanDTO dto);
}

