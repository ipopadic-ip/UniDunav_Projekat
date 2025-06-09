package com.unidunav.tipStudija.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unidunav.katedra.Repository.KatedraRepository;
import com.unidunav.katedra.dto.KatedraDTO;
import com.unidunav.katedra.model.Katedra;
import com.unidunav.katedra.service.KatedraService;
import com.unidunav.studijskiProgram.dto.StudijskiProgramDTO;
import com.unidunav.studijskiProgram.service.StudijskiProgramService;
import com.unidunav.tipStudija.dto.TipStudijaDTO;
import com.unidunav.tipStudija.model.TipStudija;
import com.unidunav.tipStudija.repository.TipStudijaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TipStudijaServiceImpl implements TipStudijaService {

    private final TipStudijaRepository tipStudijaRepository;
    private final KatedraRepository katedraRepository;
    private final KatedraService katedraService;
    private final StudijskiProgramService studijskiProgramService;

    public TipStudijaServiceImpl(TipStudijaRepository tipStudijaRepository,
                                KatedraRepository katedraRepository,
                                KatedraService katedraService,
                                StudijskiProgramService studijskiProgramService) {
        this.tipStudijaRepository = tipStudijaRepository;
        this.katedraRepository = katedraRepository;
        this.katedraService = katedraService;
        this.studijskiProgramService = studijskiProgramService;
    }

    @Override
    public TipStudijaDTO create(TipStudijaDTO dto) {
        TipStudija entity = toEntity(dto);
        entity = tipStudijaRepository.save(entity);
        return toDTO(entity);
    }

//    @Override
//    public List<TipStudijaDTO> findAll() {
//        return tipStudijaRepository.findAll()
//                .stream()
//                .map(this::toDTO)
//                .collect(Collectors.toList());
//    }
    
    @Override
    public List<TipStudijaDTO> findAll() {
        return tipStudijaRepository.findByDeletedFalse()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TipStudijaDTO> findAllAdmin() {
        return tipStudijaRepository.findAllByOrderByDeletedAsc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        TipStudija entity = tipStudijaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tip studija sa ID " + id + " nije pronađen."));
        entity.setDeleted(true);
        tipStudijaRepository.save(entity);
    }

    @Override
    public void deaktiviraj(Long id) {
        TipStudija entity = tipStudijaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tip studija sa ID " + id + " nije pronađen."));
        entity.setDeleted(true);
        tipStudijaRepository.save(entity);
    }

    @Override
    public void aktiviraj(Long id) {
        TipStudija entity = tipStudijaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tip studija sa ID " + id + " nije pronađen."));
        entity.setDeleted(false);
        tipStudijaRepository.save(entity);
    }


    @Override
    public TipStudijaDTO findById(Long id) {
        TipStudija entity = tipStudijaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tip studija sa ID " + id + " nije pronađen."));
        return toDTO(entity);
    }

    @Override
    public TipStudijaDTO update(Long id, TipStudijaDTO dto) {
        TipStudija entity = tipStudijaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tip studija sa ID " + id + " nije pronađen."));

        entity.setTip(dto.getTip());

        if (dto.getKatedra() != null && dto.getKatedra().getId() != null) {
            Katedra katedraEntity = getKatedraEntityById(dto.getKatedra().getId());
            entity.setKatedra(katedraEntity); 
        } else {
            entity.setKatedra(null);
        }

        entity = tipStudijaRepository.save(entity);
        return toDTO(entity);
    }

//    @Override
//    public void delete(Long id) {
//        if (!tipStudijaRepository.existsById(id)) {
//            throw new EntityNotFoundException("Tip studija sa ID " + id + " ne postoji.");
//        }
//        tipStudijaRepository.deleteById(id);
//    }

    private TipStudijaDTO toDTO(TipStudija entity) {
        TipStudijaDTO dto = new TipStudijaDTO();

        dto.setId(entity.getId());
        dto.setTip(entity.getTip());
        dto.setDeleted(entity.isDeleted());

        if (entity.getKatedra() != null) {
            KatedraDTO katedraDto = katedraService.findById(entity.getKatedra().getId());
            dto.setKatedra(katedraDto);
        }

        List<StudijskiProgramDTO> studijskiProgrami = studijskiProgramService.findAll()
                .stream()
                .filter(sp -> sp.getTipStudija() != null && sp.getTipStudija().getId().equals(entity.getId()))
                .collect(Collectors.toList());
        dto.setStudijskiProgrami(studijskiProgrami);

        return dto;
    }

    private TipStudija toEntity(TipStudijaDTO dto) {
        TipStudija entity = new TipStudija();

        entity.setId(dto.getId());
        entity.setTip(dto.getTip());

        if (dto.getKatedra() != null && dto.getKatedra().getId() != null) {
            Katedra katedraEntity = getKatedraEntityById(dto.getKatedra().getId());
            entity.setKatedra(katedraEntity);
        } else {
            entity.setKatedra(null);
        }

        return entity;
    }

    private Katedra getKatedraEntityById(Long id) {
        return katedraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Katedra sa ID " + id + " nije pronađena."));
    }
}
