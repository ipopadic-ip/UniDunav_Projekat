package com.unidunav.dapartman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unidunav.dapartman.dto.DepartmanDTO;
import com.unidunav.dapartman.model.Departman;
import com.unidunav.dapartman.repository.DepartmanRepository;
import com.unidunav.fakultet.dto.FakultetDTO;
import com.unidunav.fakultet.repository.FakultetRepository;
import com.unidunav.katedra.Repository.KatedraRepository;
import com.unidunav.katedra.dto.KatedraDTO;
import com.unidunav.katedra.model.Katedra;
import com.unidunav.profesor.dto.ProfesorDTO;
import com.unidunav.profesor.repository.ProfesorRepository;

@Service
public class DepartmanServiceImpl implements DepartmanService {

    private final DepartmanRepository departmanRepository;
    private final KatedraRepository katedraRepository;
    private final FakultetRepository fakultetRepository;
    private final ProfesorRepository profesorRepository;

    public DepartmanServiceImpl(DepartmanRepository departmanRepository,
                                 KatedraRepository katedraRepository,
                                 FakultetRepository fakultetRepository,
                                 ProfesorRepository profesorRepository) {
        this.departmanRepository = departmanRepository;
        this.katedraRepository = katedraRepository;
        this.fakultetRepository = fakultetRepository;
        this.profesorRepository = profesorRepository;
    }

    @Override
    public DepartmanDTO create(DepartmanDTO dto) {
        Departman departman = toEntity(dto);
        return toDTO(departmanRepository.save(departman));
    }

    @Override
    public List<DepartmanDTO> findAll() {
        return departmanRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public DepartmanDTO findById(Long id) {
        Optional<Departman> optional = departmanRepository.findById(id);
        return optional.map(this::toDTO).orElse(null);
    }

    @Override
    public DepartmanDTO update(Long id, DepartmanDTO dto) {
        if (!departmanRepository.existsById(id)) {
            return null;
        }
        Departman departman = toEntity(dto);
        departman.setId(id);
        return toDTO(departmanRepository.save(departman));
    }

    @Override
    public void delete(Long id) {
        departmanRepository.deleteById(id);
    }

    public Departman toEntity(DepartmanDTO dto) {
        Departman departman = new Departman();
        departman.setId(dto.getId());
        departman.setNaziv(dto.getNaziv());
        departman.setOpis(dto.getOpis());

        if (dto.getKatedre() != null) {
            ArrayList<Katedra> katedre = new ArrayList<>();
            for (KatedraDTO kdto : dto.getKatedre()) {
                if (kdto.getId() != null) {
                    katedraRepository.findById(kdto.getId()).ifPresent(katedre::add);
                }
            }
            departman.setKatedre(katedre);
        }

        if (dto.getFakultet() != null && dto.getFakultet().getId() != null) {
            fakultetRepository.findById(dto.getFakultet().getId()).ifPresent(departman::setFakultet);
        }

        if (dto.getSefDepartmana() != null && dto.getSefDepartmana().getId() != null) {
            profesorRepository.findById(dto.getSefDepartmana().getId()).ifPresent(departman::setSefDepartmana);
        }

        return departman;
    }

    public DepartmanDTO toDTO(Departman entity) {
        DepartmanDTO dto = new DepartmanDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setOpis(entity.getOpis());

        if (entity.getKatedre() != null) {
            dto.setKatedre(entity.getKatedre()
                    .stream()
                    .map(k -> new KatedraDTO(k.getId(), k.getNaziv(), null, k.getOpis(), null, null, null))
                    .toList());
        }

        if (entity.getFakultet() != null) {
            dto.setFakultet(new FakultetDTO(
                    entity.getFakultet().getId(),
                    entity.getFakultet().getNaziv(),
                    entity.getFakultet().getOpis(),
                    null,
                    null,
                    null,
                    null
            ));
        }

        if (entity.getSefDepartmana() != null) {
            dto.setSefDepartmana(new ProfesorDTO(
                    entity.getSefDepartmana().getId(),
                    entity.getSefDepartmana().getIme(),
                    entity.getSefDepartmana().getPrezime(),
                    entity.getSefDepartmana().getBiografija()
            ));
        }

        return dto;
    }
}