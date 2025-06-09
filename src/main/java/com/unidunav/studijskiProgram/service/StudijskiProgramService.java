package com.unidunav.studijskiProgram.service;

import java.util.List;
import java.util.Map;

import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.studijskiProgram.dto.GrupisaniProgramiDTO;
import com.unidunav.studijskiProgram.dto.StudijskiProgramDTO;

public interface StudijskiProgramService {
	StudijskiProgramDTO create(StudijskiProgramDTO dto);
//	List<StudijskiProgramDTO> findAll();
	List<StudijskiProgramDTO> findAll();
    List<StudijskiProgramDTO> findAllGroupedByFakultetSortedByDeleted();
    StudijskiProgramDTO findById(Long id);
	StudijskiProgramDTO update(Long id, StudijskiProgramDTO dto);
	void delete(Long id);
	void deaktiviraj(Long id);
    void aktiviraj(Long id);
//	Map<String, List<StudijskiProgramDTO>> findByKatedraGroupedByTipStudija(Long katedraId);
    List<GrupisaniProgramiDTO> findByKatedraGroupedByTipStudija(Long katedraId);
	Map<Integer, List<PredmetDTO>> getPredmetiPoGodinama(Long studijskiProgramId);

}
