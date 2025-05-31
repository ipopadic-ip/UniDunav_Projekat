package com.unidunav.predmet.service.silabus;

import java.util.List;

import com.unidunav.predmet.dto.SilabusDTO;

public interface SilabusService {
    SilabusDTO create(SilabusDTO dto);
    List<SilabusDTO> findAll();
    SilabusDTO findById(Long id);
    SilabusDTO update(Long id, SilabusDTO dto);
    void delete(Long id);
}
