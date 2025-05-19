package glavniPaket.service.korisnika;

import glavniPaket.dto.korisnika.PravoPristupaDTO;
import glavniPaket.model.korisnika.PravoPristupa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import glavniPaket.repository.korisnika.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PravoPristupaService {

    private final PravoPristupaRepository pravoPristupaRepository;

    @Autowired
    public PravoPristupaService(PravoPristupaRepository pravoPristupaRepository) {
        this.pravoPristupaRepository = pravoPristupaRepository;
    }

    public List<PravoPristupaDTO> findAll() {
        return pravoPristupaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PravoPristupaDTO findById(Long id) {
        PravoPristupa pravo = pravoPristupaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pravo pristupa sa ID " + id + " nije pronađeno."));
        return mapToDTO(pravo);
    }

    public PravoPristupaDTO save(PravoPristupaDTO dto) {
        PravoPristupa pravo = mapToEntity(dto);
        return mapToDTO(pravoPristupaRepository.save(pravo));
    }

    public PravoPristupaDTO update(Long id, PravoPristupaDTO dto) {
        PravoPristupa pravo = pravoPristupaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pravo pristupa sa ID " + id + " ne postoji."));
        pravo.setNaziv(dto.getNaziv());
        
        return mapToDTO(pravoPristupaRepository.save(pravo));
    }

    public void delete(Long id) {
        if (!pravoPristupaRepository.existsById(id)) {
            throw new RuntimeException("Pravo pristupa sa ID " + id + " ne postoji.");
        }
        pravoPristupaRepository.deleteById(id);
    }

    // ================= Mapping =================

    private PravoPristupaDTO mapToDTO(PravoPristupa pravo) {
        return new PravoPristupaDTO(pravo.getId(), pravo.getNaziv());
    }

    private PravoPristupa mapToEntity(PravoPristupaDTO dto) {
        return new PravoPristupa(dto.getId(), dto.getNaziv());
    }
}