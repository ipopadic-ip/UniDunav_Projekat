package com.unidunav.sluzbenik.service;

import com.unidunav.sluzbenik.dto.IznajmljivanjeKnjigeDTO;
import com.unidunav.sluzbenik.model.IznajmljivanjeKnjige;
import com.unidunav.sluzbenik.model.Knjiga;
import com.unidunav.sluzbenik.repository.IznajmljivanjeKnjigeRepository;
import com.unidunav.sluzbenik.repository.KnjigaRepository;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IznajmljivanjeKnjigeService {

    private final IznajmljivanjeKnjigeRepository iznajmljivanjeRepo;
    private final KnjigaRepository knjigaRepo;
    private final UserRepository userRepo;

    public IznajmljivanjeKnjigeService(IznajmljivanjeKnjigeRepository iznajmljivanjeRepo, KnjigaRepository knjigaRepo, UserRepository userRepo) {
        this.iznajmljivanjeRepo = iznajmljivanjeRepo;
        this.knjigaRepo = knjigaRepo;
        this.userRepo = userRepo;
    }

    public IznajmljivanjeKnjige iznajmiKnjigu(IznajmljivanjeKnjigeDTO dto) {
        Knjiga knjiga = knjigaRepo.findById(dto.getKnjiga())
            .orElseThrow(() -> new RuntimeException("Knjiga nije pronađena"));

        User korisnik = userRepo.findById(dto.getKorisnik())
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        if (dto.getBrojPrimeraka() > knjiga.getBrojDostupnih()) {
            throw new RuntimeException("Nema dovoljno dostupnih primeraka");
        }

        knjiga.setBrojDostupnih(knjiga.getBrojDostupnih() - dto.getBrojPrimeraka());
        knjigaRepo.save(knjiga);

        IznajmljivanjeKnjige iznajmljivanje = new IznajmljivanjeKnjige(
            knjiga,
            korisnik,
            dto.getBrojPrimeraka(),
            dto.getDatum() != null ? dto.getDatum() : LocalDate.now()
        );

        return iznajmljivanjeRepo.save(iznajmljivanje);
    }


    public List<IznajmljivanjeKnjige> svaIznajmljivanja() {
        return iznajmljivanjeRepo.findAll();
    }
    
    public void vratiKnjigu(Long iznajmljivanjeId) {
        IznajmljivanjeKnjige iznajmljivanje = iznajmljivanjeRepo.findById(iznajmljivanjeId)
                .orElseThrow(() -> new RuntimeException("Zapis o iznajmljivanju nije pronađen"));

        if (iznajmljivanje.isVraceno()) {
            throw new RuntimeException("Knjiga je već vraćena.");
        }

        Knjiga knjiga = iznajmljivanje.getKnjiga();
        knjiga.setBrojDostupnih(knjiga.getBrojDostupnih() + iznajmljivanje.getBrojPrimeraka());
        knjigaRepo.save(knjiga);

        iznajmljivanje.setVraceno(true);
        iznajmljivanjeRepo.save(iznajmljivanje);
    }


}
