package com.unidunav.godinaStudija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unidunav.godinaStudija.model.GodinaStudija;

public interface GodinaStudijaRepository extends JpaRepository<GodinaStudija, Long>{
	List<GodinaStudija> findByStudijskiProgramId(Long studijskiProgramId);
	
	List<GodinaStudija> findByDeletedFalse();
	List<GodinaStudija> findByDeletedTrue();
	
//	List<GodinaStudija> findByStudijskiProgramIdAndDeletedFalse(Long studijskiProgramId);
	
//	List<GodinaStudija> findByStudijskiProgramIdAndIsDeletedFalse(Long studijskiProgramId);
	
	 @Query("SELECT g FROM GodinaStudija g WHERE g.studijskiProgram.id = :studijskiProgramId AND g.deleted = false")
	    List<GodinaStudija> findAktivneGodineByStudijskiProgramId(@Param("studijskiProgramId") Long studijskiProgramId);



	List<GodinaStudija> findByDeletedFalseAndStudijskiProgramNazivContainingIgnoreCase(String naziv);
	List<GodinaStudija> findByDeletedTrueAndStudijskiProgramNazivContainingIgnoreCase(String naziv);


}
