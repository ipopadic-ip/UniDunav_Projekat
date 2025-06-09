package com.unidunav.dapartman.service;

import java.util.List;

import com.unidunav.dapartman.dto.DepartmanCreateUpdateDTO;
import com.unidunav.dapartman.dto.DepartmanDTO;
import com.unidunav.dapartman.model.Departman;

public interface DepartmanService {
	DepartmanDTO create(DepartmanCreateUpdateDTO dto);
	List<DepartmanDTO> findAll();
	DepartmanDTO findById(Long id);
	DepartmanDTO update(Long id, DepartmanCreateUpdateDTO dto);
	void delete(Long id);
	
	List<DepartmanDTO> findByFakultetId(Long fakultetId);
	
	List<DepartmanDTO> findAllActive();
	List<DepartmanDTO> findAllForAdmin();
	void deaktiviraj(Long id);
	void aktiviraj(Long id);


	
	DepartmanDTO toDTO(Departman entity);
	Departman toEntity(DepartmanDTO dto);
}

