package com.unidunav.sluzbenik.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.unidunav.sluzbenik.dto.PrimerakKnjigeDTO;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import com.unidunav.sluzbenik.dto.IznajmljivanjeKnjigeDTO;
import com.unidunav.sluzbenik.dto.KnjigaDTO;
import com.unidunav.sluzbenik.dto.KnjigaIzdavanjeDTO;
import com.unidunav.sluzbenik.model.IznajmljivanjeKnjige;
import com.unidunav.sluzbenik.model.Knjiga;
import com.unidunav.sluzbenik.model.PrimerakKnjige;
import com.unidunav.sluzbenik.repository.IznajmljivanjeKnjigeRepository;
import com.unidunav.sluzbenik.repository.KnjigaRepository;
import com.unidunav.sluzbenik.repository.PrimerakKnjigeRepository;
import com.unidunav.student.model.Student;
import com.unidunav.student.repository.StudentRepository;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class BibliotekaServiceImpl implements BibliotekaService {

    @Autowired
    private KnjigaRepository knjigaRepository;

    @Autowired
    private PrimerakKnjigeRepository primerakRepository;

    @Autowired
    private IznajmljivanjeKnjigeRepository iznajmljivanjeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<KnjigaDTO> pretraziDostupneKnjige(String naziv) {
        List<Knjiga> knjige = knjigaRepository.findByNazivContainingIgnoreCase(naziv);

        return knjige.stream().map(knjiga -> {
            // Nađi sve slobodne primerke ove knjige
            List<PrimerakKnjige> dostupni = primerakRepository
                .findByKnjigaIdAndDostupanTrue(knjiga.getId());

            int brojDostupnih = dostupni.size();

            // Uzmi prvi slobodan primerak za iznajmljivanje
            Long slobodanPrimerakId = dostupni.stream()
                .findFirst()
                .map(PrimerakKnjige::getId)
                .orElse(null);

            KnjigaDTO dto = new KnjigaDTO();
            dto.setId(knjiga.getId());
            dto.setNaziv(knjiga.getNaziv());
            dto.setAutor(knjiga.getAutor());
            dto.setBrojDostupnihPrimeraka(brojDostupnih);
            dto.setPrimerakId(slobodanPrimerakId); // ← OVO je KLJUČNO

            return dto;
        }).collect(Collectors.toList());
    }
    @Override
    public void izdajKnjigu(Long primerakId, String brojIndeksa) {
        PrimerakKnjige primerak = primerakRepository.findById(primerakId)
            .orElseThrow(() -> new IllegalArgumentException("Primerak ne postoji."));

        if (!primerak.isDostupan()) {
            throw new IllegalArgumentException("Primerak nije dostupan.");
        }

        Student student = pronadjiStudentaPoIndeksu(brojIndeksa);

        // Proveri da li je student već iznajmio isti primerak i nije ga vratio
        boolean vecIznajmio = iznajmljivanjeRepository
            .existsByPrimerakKnjigeIdAndKorisnikIdAndVracenoIsNull(primerakId, student.getId());

        if (vecIznajmio) {
            throw new IllegalStateException("Student već ima ovaj primerak iznajmljen.");
        }

        IznajmljivanjeKnjige novo = new IznajmljivanjeKnjige();
        novo.setDatum(LocalDate.now());
        novo.setVraceno(null);
        novo.setPrimerakKnjige(primerak);

        User korisnik = userRepository.findById(student.getId())
            .orElseThrow(() -> new IllegalArgumentException("Nepostojeći korisnik sa ID studenta."));
        novo.setKorisnik(korisnik);

        iznajmljivanjeRepository.save(novo);

        primerak.setDostupan(false);
        primerakRepository.save(primerak);
    }

    @Override
    public List<IznajmljivanjeKnjigeDTO> iznajmljivanjaZaStudenta(String indeks) {
        Student student = pronadjiStudentaPoIndeksu(indeks);

        List<IznajmljivanjeKnjige> lista = iznajmljivanjeRepository
            .findByKorisnikIdAndVracenoIsNull(student.getId());

        return lista.stream().map(iz -> {
            IznajmljivanjeKnjigeDTO dto = new IznajmljivanjeKnjigeDTO();
            dto.setId(iz.getId());
            dto.setDatumIznajmljivanja(iz.getDatum());
            dto.setDatumVracanja(iz.getVraceno());
            dto.setPrimerakId(iz.getPrimerakKnjige().getId());
            dto.setKnjigaNaziv(iz.getPrimerakKnjige().getKnjiga().getNaziv());
            dto.setStudentId(student.getId());
            dto.setStudentIndeks(student.getBrojIndeksa());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void vratiKnjigu(Long iznajmljivanjeId) {
        IznajmljivanjeKnjige iznajmljivanje = iznajmljivanjeRepository.findById(iznajmljivanjeId)
            .orElseThrow(() -> new IllegalArgumentException("Iznajmljivanje ne postoji."));

        if (iznajmljivanje.getVraceno() != null) {
            throw new IllegalStateException("Knjiga je već vraćena.");
        }

        iznajmljivanje.setVraceno(LocalDate.now());
        iznajmljivanjeRepository.save(iznajmljivanje);

        PrimerakKnjige primerak = iznajmljivanje.getPrimerakKnjige();
        primerak.setDostupan(true);
        primerakRepository.save(primerak);
    }

    private Student pronadjiStudentaPoIndeksu(String indeks) {
        List<Student> studenti = studentRepository.findByBrojIndeksaContainingIgnoreCaseAndUserNotDeleted(indeks);
        if (studenti.isEmpty()) {
            throw new IllegalArgumentException("Student sa datim indeksom ne postoji.");
        }
        return studenti.get(0); // možeš dodati logiku za više rezultata ako treba
    }
    @Override
    public List<KnjigaIzdavanjeDTO> pretraziDostupneKnjigeZaStudenta(String indeks) {
        Student student = pronadjiStudentaPoIndeksu(indeks);
        List<Knjiga> sveKnjige = knjigaRepository.findAll();
        List<IznajmljivanjeKnjige> iznajmljivanja = iznajmljivanjeRepository
            .findByKorisnikIdAndVracenoIsNull(student.getId());

        return sveKnjige.stream().map(knjiga -> {
            // svi primerci te knjige
            List<PrimerakKnjige> primerci = primerakRepository.findByKnjigaId(knjiga.getId());

            // da li postoji slobodan
            Optional<PrimerakKnjige> slobodan = primerci.stream().filter(PrimerakKnjige::isDostupan).findFirst();

            boolean dostupna = slobodan.isPresent();

            // da li je student već iznajmio neku verziju te knjige
            boolean vecIznajmljena = iznajmljivanja.stream()
                .anyMatch(iz -> iz.getPrimerakKnjige().getKnjiga().getId().equals(knjiga.getId()));

            return new KnjigaIzdavanjeDTO(
                knjiga.getId(),
                knjiga.getNaziv(),
                knjiga.getAutor(),
                dostupna,
                vecIznajmljena,
                slobodan.map(PrimerakKnjige::getId).orElse(null)
            );
        }).collect(Collectors.toList());
    }
    
    @Override
    public KnjigaDTO dodajKnjigu(KnjigaDTO dto) {
        Knjiga knjiga = new Knjiga();
        knjiga.setNaziv(dto.getNaziv());
        knjiga.setAutor(dto.getAutor());
        
        knjiga.setZanr(dto.getZanr());
        knjiga.setGodinaIzdavanja(dto.getGodinaIzdavanja());
        knjiga.setIzdavac(dto.getIzdavac());

        Knjiga sacuvana = knjigaRepository.save(knjiga);

        KnjigaDTO response = new KnjigaDTO();
        response.setId(sacuvana.getId());
        response.setNaziv(sacuvana.getNaziv());
        response.setAutor(sacuvana.getAutor());
        response.setZanr(sacuvana.getZanr());
        response.setIzdavac(sacuvana.getIzdavac());
        response.setGodinaIzdavanja(sacuvana.getGodinaIzdavanja());
        response.setBrojDostupnihPrimeraka(0); 
        response.setPrimerakId(null); 

        return response;
    }
    @Override
    public List<KnjigaDTO> sveKnjige() {
        List<Knjiga> knjige = knjigaRepository.findAll();

        return knjige.stream().map(knjiga -> {
            KnjigaDTO dto = new KnjigaDTO();
            dto.setId(knjiga.getId());
            dto.setNaziv(knjiga.getNaziv());
            dto.setAutor(knjiga.getAutor());

            long brojDostupnih = ((Collection<PrimerakKnjige>) knjiga.getPrimerci()).stream()
                .filter(PrimerakKnjige::isDostupan)
                .count();
            dto.setBrojDostupnihPrimeraka((int) brojDostupnih);

            if (!knjiga.getPrimerci().isEmpty()) {
                dto.setPrimerakId(knjiga.getPrimerci().get(0).getId());
            } else {
                dto.setPrimerakId(null);
            }

            return dto;
        }).collect(Collectors.toList());
    }
    @Override
    public PrimerakKnjigeDTO dodajPrimerak(PrimerakKnjigeDTO dto) {
        Knjiga knjiga = knjigaRepository.findById(dto.getKnjigaId())
            .orElseThrow(() -> new RuntimeException("Knjiga ne postoji"));

        PrimerakKnjige primerak = new PrimerakKnjige();
        primerak.setDostupan(dto.isDostupan());
        primerak.setIsbn(dto.getIsbn()); // dodaj ovo
        primerak.setKnjiga(knjiga);

        knjiga.setBrojPrimeraka(knjiga.getBrojPrimeraka() + 1);
        knjigaRepository.save(knjiga);

        PrimerakKnjige sacuvan = primerakRepository.save(primerak);

        PrimerakKnjigeDTO response = new PrimerakKnjigeDTO();
        response.setId(sacuvan.getId());
        response.setDostupan(sacuvan.isDostupan());
        response.setIsbn(sacuvan.getIsbn()); // dodaj ovo
        response.setKnjigaId(knjiga.getId());

        return response;
    }
    @Override
    public List<PrimerakKnjigeDTO> getPrimerciZaKnjigu(Long knjigaId) {
        return primerakRepository.findByKnjigaId(knjigaId).stream().map(p -> {
            PrimerakKnjigeDTO dto = new PrimerakKnjigeDTO();
            dto.setId(p.getId());
            dto.setDostupan(p.isDostupan());
            dto.setKnjigaId(p.getKnjiga().getId());
            return dto;
        }).collect(Collectors.toList());
    }

@Override
@Transactional
public void obrisiPrimerakPoIsbn(String isbn) {
    PrimerakKnjige primerak = primerakRepository.findByIsbn(isbn)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Primerak sa datim ISBN-om ne postoji"));

    Knjiga knjiga = primerak.getKnjiga();

    // Uklanjanje primerka
    primerakRepository.delete(primerak);

    // Smanji broj primeraka u knjizi ako je > 0
    if (knjiga.getBrojPrimeraka() > 0) {
        knjiga.setBrojPrimeraka(knjiga.getBrojPrimeraka() - 1);
        knjigaRepository.save(knjiga);
    }
}
    
}