package com.unidunav.sluzbenik.service;

import com.unidunav.sluzbenik.dto.SluzbenikDTO;

import java.util.List;

public interface SluzbenikService {

    SluzbenikDTO create(SluzbenikDTO dto);
    List<SluzbenikDTO> findAll();
    SluzbenikDTO findById(Long id);
    SluzbenikDTO update(Long id, SluzbenikDTO dto);
    void delete(Long id);
}
