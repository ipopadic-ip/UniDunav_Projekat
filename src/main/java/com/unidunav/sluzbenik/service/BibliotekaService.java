package com.unidunav.sluzbenik.service;

import java.util.List;

import com.unidunav.sluzbenik.dto.IznajmljivanjeKnjigeDTO;
import com.unidunav.sluzbenik.dto.KnjigaDTO;
import com.unidunav.sluzbenik.dto.KnjigaIzdavanjeDTO;

public interface BibliotekaService {

    List<KnjigaDTO> pretraziDostupneKnjige(String naziv);

    void izdajKnjigu(Long primerakId, String brojIndeksa);

    List<IznajmljivanjeKnjigeDTO> iznajmljivanjaZaStudenta(String indeks);

    void vratiKnjigu(Long iznajmljivanjeId);

    List<KnjigaIzdavanjeDTO> pretraziDostupneKnjigeZaStudenta(String indeks);
}