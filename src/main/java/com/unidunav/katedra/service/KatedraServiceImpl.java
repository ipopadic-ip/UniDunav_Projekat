package com.unidunav.katedra.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unidunav.katedra.dto.KatedraDTO;
import com.unidunav.katedra.model.Katedra;
import com.unidunav.katedra.Repository.KatedraRepository;
import com.unidunav.dapartman.model.Departman;
import com.unidunav.dapartman.repository.DepartmanRepository;
import com.unidunav.predmet.model.Predmet;
import com.unidunav.predmet.service.PredmetService;
import com.unidunav.tipStudija.model.TipStudija;
import com.unidunav.tipStudija.repository.TipStudijaRepository;
import com.unidunav.profesor.model.Profesor;
import com.unidunav.profesor.repository.ProfesorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class KatedraServiceImpl implements KatedraService {

    private final KatedraRepository katedraRepository;
    private final DepartmanRepository departmanRepository;
    private final TipStudijaRepository tipStudijaRepository;
    private final ProfesorRepository profesorRepository;
    private final PredmetService predmetService;

    public KatedraServiceImpl(KatedraRepository katedraRepository,
                              DepartmanRepository departmanRepository,
                              TipStudijaRepository tipStudijaRepository,
                              ProfesorRepository profesorRepository,
                              PredmetService predmetService) {
        this.katedraRepository = katedraRepository;
        this.departmanRepository = departmanRepository;
        this.tipStudijaRepository = tipStudijaRepository;
        this.profesorRepository = profesorRepository;
        this.predmetService = predmetService;
    }

    @Override
    public KatedraDTO create(KatedraDTO dto) {
        Katedra entity = toEntity(dto);
        entity = katedraRepository.save(entity);
        return toDTO(entity);
    }

    @Override
    public List<KatedraDTO> findAll() {
        return katedraRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public KatedraDTO findById(Long id) {
        Katedra entity = katedraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Katedra sa ID " + id + " nije pronađena."));
        return toDTO(entity);
    }

    @Override
    public KatedraDTO update(Long id, KatedraDTO dto) {
        Katedra entity = katedraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Katedra sa ID " + id + " nije pronađena."));

        entity.setNaziv(dto.getNaziv());
        entity.setOpis(dto.getOpis());

        if (dto.getDepartman() != null && dto.getDepartman().getId() != null) {
            Departman departman = departmanRepository.findById(dto.getDepartman().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Departman sa ID " + dto.getDepartman().getId() + " nije pronađen."));
            entity.setDepartman(departman);
        } else {
            entity.setDepartman(null);
        }

        if (dto.getTipoviStudija() != null) {
            entity.getTipoviStudija().clear();
            for (var tipDto : dto.getTipoviStudija()) {
                TipStudija tipStudija = tipStudijaRepository.findById(tipDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Tip studija sa ID " + tipDto.getId() + " nije pronađen."));
                entity.getTipoviStudija().add(tipStudija);
            }
        } else {
            entity.getTipoviStudija().clear();
        }

        if (dto.getSefKatedre() != null && dto.getSefKatedre().getId() != null) {
            Profesor sef = profesorRepository.findById(dto.getSefKatedre().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Profesor (šef katedre) sa ID " + dto.getSefKatedre().getId() + " nije pronađen."));
            entity.setSefKatedre(sef);
        } else {
            entity.setSefKatedre(null);
        }

        // Update predmeta (ako treba da ih menjaš pri update-u)
//        if (dto.getPredmeti() != null) {
//            entity.getPredmeti().clear();
//            for (var predmetDto : dto.getPredmeti()) {
//                Predmet predmet = predmetService.toEntity(predmetDto);
//                entity.getPredmeti().add(predmet);
//            }
//        } else {
//            entity.getPredmeti().clear();
//        }

        entity = katedraRepository.save(entity);
        return toDTO(entity);
    }

    @Override
    public void delete(Long id) {
        if (!katedraRepository.existsById(id)) {
            throw new EntityNotFoundException("Katedra sa ID " + id + " ne postoji.");
        }
        katedraRepository.deleteById(id);
    }

    private KatedraDTO toDTO(Katedra entity) {
        KatedraDTO dto = new KatedraDTO();

        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setOpis(entity.getOpis());

        // Departman
        if (entity.getDepartman() != null) {
            var d = entity.getDepartman();
            var departmanDTO = new com.unidunav.dapartman.dto.DepartmanDTO();
            departmanDTO.setId(d.getId());
            departmanDTO.setNaziv(d.getNaziv());
            dto.setDepartman(departmanDTO);
        }

        // Tipovi studija
        if (entity.getTipoviStudija() != null) {
            List<com.unidunav.tipStudija.dto.TipStudijaDTO> tipDtoList = entity.getTipoviStudija().stream()
                    .map(t -> {
                        var tipDto = new com.unidunav.tipStudija.dto.TipStudijaDTO();
                        tipDto.setId(t.getId());
                        tipDto.setTip(t.getTip());
                        return tipDto;
                    }).collect(Collectors.toList());
            dto.setTipoviStudija(tipDtoList);
        }

        // Šef katedre
        if (entity.getSefKatedre() != null) {
            var sef = entity.getSefKatedre();
            var sefDto = new com.unidunav.profesor.dto.ProfesorDTO();
            sefDto.setId(sef.getId());
//            sefDto.setIme(sef.getIme());
//            sefDto.setPrezime(sef.getPrezime());
            dto.setSefKatedre(sefDto);
        }

        // Predmeti
//        if (entity.getPredmeti() != null) {
//            List<com.unidunav.predmet.dto.PredmetDTO> predmetiDto = entity.getPredmeti().stream()
//                    .map(predmetService::toDTO)
//                    .collect(Collectors.toList());
//            dto.setPredmeti(predmetiDto);
//        }

        return dto;
    }

    private Katedra toEntity(KatedraDTO dto) {
        Katedra entity = new Katedra();

        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        entity.setOpis(dto.getOpis());

        if (dto.getDepartman() != null && dto.getDepartman().getId() != null) {
            Departman departman = departmanRepository.findById(dto.getDepartman().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Departman sa ID " + dto.getDepartman().getId() + " nije pronađen."));
            entity.setDepartman(departman);
        }

        if (dto.getTipoviStudija() != null) {
            entity.getTipoviStudija().clear();
            for (var tipDto : dto.getTipoviStudija()) {
                TipStudija tipStudija = tipStudijaRepository.findById(tipDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Tip studija sa ID " + tipDto.getId() + " nije pronađen."));
                entity.getTipoviStudija().add(tipStudija);
            }
        }

        if (dto.getSefKatedre() != null && dto.getSefKatedre().getId() != null) {
            Profesor sef = profesorRepository.findById(dto.getSefKatedre().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Profesor (šef katedre) sa ID " + dto.getSefKatedre().getId() + " nije pronađen."));
            entity.setSefKatedre(sef);
        }

//        if (dto.getPredmeti() != null) {
//            entity.getPredmeti().clear();
//            for (var predmetDto : dto.getPredmeti()) {
//                Predmet predmet = predmetService.toEntity(predmetDto);
//                entity.getPredmeti().add(predmet);
//            }
//        }

        return entity;
    }
}
