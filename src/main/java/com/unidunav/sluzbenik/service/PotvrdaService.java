package com.unidunav.sluzbenik.service;

import com.unidunav.sluzbenik.dto.PotvrdaDTO;
import java.util.List;

public interface PotvrdaService {
    PotvrdaDTO izdajPotvrdu(PotvrdaDTO dto);
    List<PotvrdaDTO> svePotvrde();
    List<PotvrdaDTO> potvrdeZaStudenta(Long studentId);
    List<PotvrdaDTO> getAllPotvrde();
	PotvrdaDTO createPotvrda(PotvrdaDTO dto);
}
