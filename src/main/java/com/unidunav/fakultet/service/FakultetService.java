package com.unidunav.fakultet.service;

import java.util.List;

import com.unidunav.fakultet.dto.FakultetDTO;
import com.unidunav.fakultet.dto.FakultetSimpleDTO;
import com.unidunav.fakultet.model.Fakultet;

public interface FakultetService {
	FakultetDTO create(FakultetDTO dto);
	List<FakultetDTO> findAll();
	FakultetDTO findById(Long id);
	FakultetDTO update(Long id, FakultetDTO dto);
	FakultetSimpleDTO updateSimple(Long id, FakultetSimpleDTO dto);
	FakultetSimpleDTO createSimple(FakultetSimpleDTO dto);
	void delete(Long id);
	
    FakultetDTO toDTO(Fakultet entity);
    Fakultet toEntity(FakultetDTO dto);
    
    List<FakultetSimpleDTO> findAllSimple();
    FakultetSimpleDTO findSimpleById(Long id);
    FakultetSimpleDTO toSimpleDTO(Fakultet entity);
    
    List<FakultetSimpleDTO> findAllSimpleAdmin();
    void setDeleted(Long id, boolean deleted);

    
}
