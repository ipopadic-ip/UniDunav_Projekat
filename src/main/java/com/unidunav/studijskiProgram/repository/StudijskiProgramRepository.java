package com.unidunav.studijskiProgram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unidunav.studijskiProgram.model.StudijskiProgram;
@Repository
public interface StudijskiProgramRepository extends JpaRepository<StudijskiProgram, Long>{
	List<StudijskiProgram> findByKatedraId(Long katedraId);
	
	List<StudijskiProgram> findByDeletedFalse();
	List<StudijskiProgram> findAllByOrderByDeletedAsc();

}
