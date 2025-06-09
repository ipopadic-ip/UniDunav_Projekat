package com.unidunav.katedra.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unidunav.katedra.model.Katedra;

public interface KatedraRepository extends JpaRepository<Katedra, Long> {
	List<Katedra> findByDepartmanId(Long departmanId);
	
	List<Katedra> findByDeletedFalse();

	List<Katedra> findByDepartmanIdAndDeletedFalse(Long departmanId);

	@Query("SELECT k FROM Katedra k ORDER BY k.deleted ASC, k.departman.fakultet.naziv ASC")
	List<Katedra> findAllSortiranoPoAktivnostiIFakultetu();

}
