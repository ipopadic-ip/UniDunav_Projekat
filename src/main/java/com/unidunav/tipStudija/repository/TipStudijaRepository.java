package com.unidunav.tipStudija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidunav.tipStudija.model.TipStudija;

public interface TipStudijaRepository extends JpaRepository<TipStudija, Long>{
	 List<TipStudija> findByDeletedFalse();
	 List<TipStudija> findAllByOrderByDeletedAsc();
}
