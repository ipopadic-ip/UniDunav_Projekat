package com.unidunav.dokument.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unidunav.dokument.model.VerzijaDokumenta;

public interface VerzijaDokumentaRepository extends JpaRepository<VerzijaDokumenta, Long> {
    List<VerzijaDokumenta> findByDokumentIdOrderByBrojVerzijeDesc(Long dokumentId);
    
    List<VerzijaDokumenta> findByDokumentIdAndDeletedFalse(Long dokumentId);
    List<VerzijaDokumenta> findByDokumentId(Long dokumentId);
    
    @Query("SELECT v FROM VerzijaDokumenta v " +
 	       "WHERE v.brojVerzije = (" +
 	       "   SELECT MAX(v2.brojVerzije) FROM VerzijaDokumenta v2 " +
 	       "   WHERE v2.dokument.id = v.dokument.id AND v2.deleted = false" +
 	       ") AND v.deleted = false")
 	List<Object[]> findPoslednjeVerzijePoDokumentima();
}

