package com.unidunav.sluzbenik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidunav.sluzbenik.model.TrebovanjeKnjige;

public interface TrebovanjeKnjigeRepository extends JpaRepository<TrebovanjeKnjige, Long> {
}