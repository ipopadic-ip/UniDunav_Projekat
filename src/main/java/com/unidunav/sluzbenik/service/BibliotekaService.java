package com.unidunav.sluzbenik.service;

import java.util.List;

import com.unidunav.sluzbenik.dto.IznajmljivanjeKnjigeDTO;
import com.unidunav.sluzbenik.dto.KnjigaDTO;
import com.unidunav.sluzbenik.dto.KnjigaIzdavanjeDTO;
import com.unidunav.sluzbenik.dto.PrimerakKnjigeDTO;

public interface BibliotekaService {

    List<KnjigaDTO> pretraziDostupneKnjige(String naziv);

    void izdajKnjigu(Long primerakId, String brojIndeksa);

    List<IznajmljivanjeKnjigeDTO> iznajmljivanjaZaStudenta(String indeks);

    void vratiKnjigu(Long iznajmljivanjeId);

    List<KnjigaIzdavanjeDTO> pretraziDostupneKnjigeZaStudenta(String indeks);
    
    KnjigaDTO dodajKnjigu(KnjigaDTO dto);

	List<KnjigaDTO> sveKnjige();

	PrimerakKnjigeDTO dodajPrimerak(PrimerakKnjigeDTO dto);

	List<PrimerakKnjigeDTO> getPrimerciZaKnjigu(Long knjigaId);

	void obrisiPrimerakPoIsbn(String isbn);
}