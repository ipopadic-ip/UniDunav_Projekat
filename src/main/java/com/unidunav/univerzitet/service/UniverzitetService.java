package com.unidunav.univerzitet.service;

import java.util.List;

import com.unidunav.univerzitet.dto.UniverzitetDTO;
import com.unidunav.univerzitet.model.Univerzitet;

public interface UniverzitetService {
	
	UniverzitetDTO create(UniverzitetDTO dto);
	List<UniverzitetDTO> findAll();
	UniverzitetDTO findById(Long id);
	UniverzitetDTO update(Long id, UniverzitetDTO dto);
	void delete(Long id);
	
	UniverzitetDTO toDTO(Univerzitet entity);
	Univerzitet toEntity(UniverzitetDTO dto);
}
