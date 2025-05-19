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

    private final DodeljenoPravoPristupaRepository dodeljenoRepo;
    private final RegistrovaniKorisnikRepository korisnikRepo;
    private final PravoPristupaRepository pravoRepo;

    @Autowired
    public DodeljenoPravoPristupaService(
            DodeljenoPravoPristupaRepository dodeljenoRepo,
            RegistrovaniKorisnikRepository korisnikRepo,
            PravoPristupaRepository pravoRepo) {
        this.dodeljenoRepo = dodeljenoRepo;
        this.korisnikRepo = korisnikRepo;
        this.pravoRepo = pravoRepo;
    }

    public List<DodeljenoPravoPristupaDTO> findAll() {
        return dodeljenoRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<DodeljenoPravoPristupaDTO> findByKorisnikId(Long korisnikId) {
        return dodeljenoRepo.findByKorisnikId(korisnikId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public DodeljenoPravoPristupaDTO findById(Long id) {
        DodeljenoPravoPristupa dpp = dodeljenoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Dodeljeno pravo pristupa sa ID " + id + " nije pronađeno."));
        return mapToDTO(dpp);
    }

    public DodeljenoPravoPristupaDTO save(DodeljenoPravoPristupaDTO dto) {
        DodeljenoPravoPristupa entity = mapToEntity(dto);
        return mapToDTO(dodeljenoRepo.save(entity));
    }

    public void delete(Long id) {
        if (!dodeljenoRepo.existsById(id)) {
            throw new RuntimeException("Dodeljeno pravo sa ID " + id + " ne postoji.");
        }
        dodeljenoRepo.deleteById(id);
    }

    // ================= Mapping =================

    private DodeljenoPravoPristupaDTO mapToDTO(DodeljenoPravoPristupa entity) {
        return new DodeljenoPravoPristupaDTO(
                entity.getId(),
                entity.getKorisnik().getId(),
                entity.getPravoPristupa().getId()
        );
    }

    private DodeljenoPravoPristupa mapToEntity(DodeljenoPravoPristupaDTO dto) {
        RegistrovaniKorisnik korisnik = korisnikRepo.findById(dto.getKorisnikId())
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen sa ID: " + dto.getKorisnikId()));

        PravoPristupa pravo = pravoRepo.findById(dto.getPravoPristupaId())
                .orElseThrow(() -> new RuntimeException("Pravo pristupa nije pronađeno sa ID: " + dto.getPravoPristupaId()));

        return new DodeljenoPravoPristupa(dto.getId(), korisnik, pravo);
    }
}
