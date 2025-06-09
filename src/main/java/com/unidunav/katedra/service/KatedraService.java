package com.unidunav.katedra.service;

import java.util.List;

import com.unidunav.katedra.dto.KatedraCreateUpdateDTO;
import com.unidunav.katedra.dto.KatedraDTO;

public interface KatedraService {
	KatedraDTO create(KatedraDTO dto);
	List<KatedraDTO> findAll();
	KatedraDTO findById(Long id);
	KatedraDTO update(Long id, KatedraDTO dto);
	void delete(Long id);
	List<KatedraDTO> findByDepartmanId(Long departmanId);
	
	List<KatedraDTO> findAllAktivne();

	List<KatedraDTO> findAllAdmin();

	List<KatedraDTO> findAktivneByDepartmanId(Long departmanId);

	void setDeleted(Long id, boolean deleted);

	KatedraDTO create(KatedraCreateUpdateDTO dto);
	KatedraDTO update(Long id, KatedraCreateUpdateDTO dto);


}
