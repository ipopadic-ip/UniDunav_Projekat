package com.unidunav.administrator.service;
import com.unidunav.administrator.dto.AdministratorDTO;
import java.util.List;

public interface AdministratorService {
	AdministratorDTO create(AdministratorDTO administratorDTO);
    List<AdministratorDTO> findAll();
    AdministratorDTO findById(Long id);
    AdministratorDTO update(Long id, AdministratorDTO administratorDTO);
    void delete(Long id);
}
