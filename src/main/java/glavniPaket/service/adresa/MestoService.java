package glavniPaket.service.adresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.adresa.MestoDTO;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.repository.adresa.MestoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MestoService {

    private final MestoRepository mestoRepo;

    @Autowired
    public MestoService(MestoRepository mestoRepo) {
        this.mestoRepo = mestoRepo;
    }

    public List<MestoDTO> findAll() {
        return mestoRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public MestoDTO findById(Long id) {
        return mestoRepo.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Mesto nije pronađeno"));
    }

    public List<MestoDTO> findByNaziv(String naziv) {
        return mestoRepo.findByNazivContainingIgnoreCase(naziv).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public MestoDTO save(MestoDTO dto) {
        Mesto entity = mapToEntity(dto);
        return mapToDTO(mestoRepo.save(entity));
    }

    public void delete(Long id) {
        mestoRepo.deleteById(id);
    }

    // ========== MAPERI ==========

    private MestoDTO mapToDTO(Mesto entity) {
        return new MestoDTO(entity.getId(), entity.getNaziv());
    }

    private Mesto mapToEntity(MestoDTO dto) {
        Mesto m = new Mesto();
        m.setId(dto.getId());
        m.setNaziv(dto.getNaziv());
        return m;
    }
}
