package com.unidunav.godinaStudija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidunav.godinaStudija.model.GodinaStudija;

public interface GodinaStudijaRepository extends JpaRepository<GodinaStudija, Long>{
	List<GodinaStudija> findByStudijskiProgramId(Long studijskiProgramId);

}
