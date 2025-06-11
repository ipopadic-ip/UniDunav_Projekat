package com.unidunav.dokument.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidunav.dokument.model.VerzijaDokumenta;

public interface VerzijaDokumentaRepository extends JpaRepository<VerzijaDokumenta, Long> {
    List<VerzijaDokumenta> findByDokumentIdOrderByBrojVerzijeDesc(Long dokumentId);
    
    List<VerzijaDokumenta> findByDokumentIdAndDeletedFalse(Long dokumentId);
    List<VerzijaDokumenta> findByDokumentId(Long dokumentId);
}

