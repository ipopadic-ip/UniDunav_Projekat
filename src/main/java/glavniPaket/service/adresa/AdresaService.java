package glavniPaket.service.adresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glavniPaket.dto.adresa.AdresaDTO;
import glavniPaket.dto.adresa.MestoDTO;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.repository.adresa.AdresaRepository;
import glavniPaket.repository.adresa.MestoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdresaService {

    private final AdresaRepository adresaRepo;
    private final MestoRepository mestoRepo;

    @Autowired
    public AdresaService(AdresaRepository adresaRepo, MestoRepository mestoRepo) {
        this.adresaRepo = adresaRepo;
        this.mestoRepo = mestoRepo;
    }

    public List<AdresaDTO> findAll() {
        return adresaRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AdresaDTO findById(Long id) {
        return adresaRepo.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Adresa nije pronađena"));
    }

    public List<AdresaDTO> findByMestoId(Long mestoId) {
        return adresaRepo.findByMestoId(mestoId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AdresaDTO> findByUlica(String ulica) {
        return adresaRepo.findByUlicaContainingIgnoreCase(ulica).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AdresaDTO save(AdresaDTO dto) {
        Adresa entity = mapToEntity(dto);
        return mapToDTO(adresaRepo.save(entity));
    }

    public void delete(Long id) {
        adresaRepo.deleteById(id);
    }

    // ========== MAPERI ==========

    private AdresaDTO mapToDTO(Adresa entity) {
        Mesto mesto = entity.getMesto();
        MestoDTO mestoDTO = new MestoDTO(mesto.getId(), mesto.getNaziv());

        return new AdresaDTO(
                entity.getId(),
                entity.getUlica(),
                entity.getBroj(),
                mestoDTO
        );
    }

    private Adresa mapToEntity(AdresaDTO dto) {
        Adresa a = new Adresa();
        a.setId(dto.getId());
        a.setUlica(dto.getUlica());
        a.setBroj(dto.getBroj());

        Mesto mesto = mestoRepo.findById(dto.getMesto().getId())
                .orElseThrow(() -> new RuntimeException("Mesto nije pronađeno"));
        a.setMesto(mesto);

        return a;
    }
}
