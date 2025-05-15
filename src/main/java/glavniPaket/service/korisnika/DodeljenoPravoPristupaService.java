package glavniPaket.service.korisnika;

import glavniPaket.dto.korisnika.*;
import glavniPaket.model.korisnika.DodeljenoPravoPristupa;
import glavniPaket.model.korisnika.PravoPristupa;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import glavniPaket.repository.korisnika.*;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class DodeljenoPravoPristupaService {

    @Autowired
    private DodeljenoPravoPristupaRepository dodeljenoRepo;

    @Autowired
    private RegistrovaniKorisnikRepository korisnikRepo;

    @Autowired
    private PravoPristupaRepository pravoRepo;

    public DodeljenoPravoPristupaDTO dodeliPravo(Integer korisnikId, Integer pravoId) {
        RegistrovaniKorisnik korisnik = korisnikRepo.findById(korisnikId)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));
        PravoPristupa pravo = pravoRepo.findById(pravoId)
                .orElseThrow(() -> new RuntimeException("Pravo pristupa nije pronađeno"));

        DodeljenoPravoPristupa dodeljeno = new DodeljenoPravoPristupa(korisnik, pravo);
        dodeljeno = dodeljenoRepo.save(dodeljeno);

        return new DodeljenoPravoPristupaDTO(dodeljeno.getId(), korisnikId, pravoId);
    }

    public List<DodeljenoPravoPristupaDTO> svaPravaZaKorisnika(Integer korisnikId) { /////OVDE JE PORAZAN, NMG NIKAKO NAMESTITI
        return dodeljenoRepo.findByKorisnikId(korisnikId).stream()
                .map(p -> new DodeljenoPravoPristupaDTO())	////////////
                .collect(Collectors.toList());
    }

    public void obrisiPravo(Integer dodeljenoId) {
        dodeljenoRepo.deleteById(dodeljenoId);
    }
}
