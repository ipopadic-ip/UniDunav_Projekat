package com.unidunav.godinaStudija.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.unidunav.godinaStudija.dto.GodinaStudijaCreateUpdateDTO;
import com.unidunav.godinaStudija.dto.GodinaStudijaDTO;
import com.unidunav.godinaStudija.dto.GodinaStudijaReadDTO;

public interface GodinaStudijaService {

	List<GodinaStudijaReadDTO> findAllAktivne(Optional<String> nazivPrograma);
	Map<String, List<GodinaStudijaReadDTO>> findAllZaAdmin(Optional<String> nazivPrograma);
	GodinaStudijaReadDTO deaktiviraj(Long id);
	GodinaStudijaReadDTO aktiviraj(Long id);
	
	GodinaStudijaReadDTO create(GodinaStudijaCreateUpdateDTO dto);
	GodinaStudijaReadDTO update(Long id, GodinaStudijaCreateUpdateDTO dto);
	List<GodinaStudijaReadDTO> findAll();
	GodinaStudijaReadDTO findById(Long id);

//	GodinaStudijaDTO create(GodinaStudijaDTO dto);
//	List<GodinaStudijaDTO> findAll();
//	GodinaStudijaDTO findById(Long id);
//	GodinaStudijaDTO update(Long id, GodinaStudijaDTO dto);
//	void delete(Long id);
}
