package com.unidunav.obavestenje.repository;

import com.unidunav.obavestenje.model.Obavestenje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObavestenjeRepository extends JpaRepository<Obavestenje, Long> {
}