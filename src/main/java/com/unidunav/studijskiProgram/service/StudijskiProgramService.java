package com.unidunav.studijskiProgram.service;

import java.util.List;

import com.unidunav.studijskiProgram.dto.StudijskiProgramDTO;

public interface StudijskiProgramService {
	StudijskiProgramDTO create(StudijskiProgramDTO dto);
	List<StudijskiProgramDTO> findAll();
	StudijskiProgramDTO findById(Long id);
	StudijskiProgramDTO update(Long id, StudijskiProgramDTO dto);
	void delete(Long id);
}
