package com.unidunav.profesor.service;

import com.unidunav.profesor.dto.ProfesorDTO;
import com.unidunav.profesor.model.Profesor;

import java.util.List;

public interface ProfesorService {

    ProfesorDTO create(ProfesorDTO dto);
    List<ProfesorDTO> findAll();
    ProfesorDTO findById(Long id);
    ProfesorDTO update(Long id, ProfesorDTO dto);
    void delete(Long id);
    
    ProfesorDTO toDTO(Profesor entity);
    Profesor toEntity(ProfesorDTO dto);
}
