package com.unidunav.fakultet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unidunav.dapartman.dto.DepartmanDTO;
import com.unidunav.dapartman.model.Departman;
import com.unidunav.dapartman.service.DepartmanService;
import com.unidunav.fakultet.dto.FakultetDTO;
import com.unidunav.fakultet.dto.FakultetSimpleDTO;
import com.unidunav.fakultet.model.Fakultet;
import com.unidunav.fakultet.repository.FakultetRepository;
import com.unidunav.profesor.model.Profesor;
import com.unidunav.profesor.repository.ProfesorRepository;
import com.unidunav.profesor.service.ProfesorService;
import com.unidunav.univerzitet.model.Univerzitet;
import com.unidunav.univerzitet.repository.UniverzitetRepository;
import com.unidunav.univerzitet.service.UniverzitetService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class FakultetServiceImpl implements FakultetService {

    private final FakultetRepository fakultetRepository;
    private final ProfesorRepository profesorRepository;
    private final UniverzitetService univerzitetService;
    private final ProfesorService profesorService;
    private final DepartmanService departmanService;
    private final UniverzitetRepository univerzitetRepository;

    public FakultetServiceImpl(FakultetRepository fakultetRepository,
                              UniverzitetService univerzitetService,
                              ProfesorService profesorService,
                              DepartmanService departmanService,  ProfesorRepository profesorRepository, UniverzitetRepository univerzitetRepository) {
        this.fakultetRepository = fakultetRepository;
        this.univerzitetService = univerzitetService;
        this.profesorService = profesorService;
        this.departmanService = departmanService;
        this.profesorRepository = profesorRepository;
        this.univerzitetRepository = univerzitetRepository;
    }

    @Override
    public FakultetDTO create(FakultetDTO dto) {
        Fakultet entity = toEntity(dto);
        entity = fakultetRepository.save(entity);
        return toDTO(entity);
    }

    @Override
    public List<FakultetDTO> findAll() {
        return fakultetRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FakultetDTO findById(Long id) {
        Fakultet entity = fakultetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fakultet sa ID " + id + " nije pronađen."));
        return toDTO(entity);
    }

    @Override
    public FakultetDTO update(Long id, FakultetDTO dto) {
        Fakultet entity = fakultetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fakultet sa ID " + id + " nije pronađen."));

        entity.setNaziv(dto.getNaziv());
        entity.setEmail(dto.getEmail());
        entity.setOpis(dto.getOpis());
        entity.setLokacija(dto.getLokacija());
        entity.setBrojTelefona(dto.getBrojTelefona());

        if (dto.getUniverzitet() != null && dto.getUniverzitet().getId() != null) {
            entity.setUniverzitet(univerzitetService.toEntity(dto.getUniverzitet()));
        } else {
            entity.setUniverzitet(null);
        }

        if (dto.getDekan() != null && dto.getDekan().getId() != null) {
            entity.setDekan(profesorService.toEntity(dto.getDekan()));
        } else {
            entity.setDekan(null);
        }

        if (dto.getDepartmani() != null) {
            List<Departman> departmani = dto.getDepartmani()
                    .stream()
                    .map(departmanService::toEntity)
                    .collect(Collectors.toCollection(ArrayList::new));
            entity.setDepartmani(new ArrayList<>(departmani));
        } else {
            entity.setDepartmani(new ArrayList<>());
        }

        return toDTO(fakultetRepository.save(entity));
    }
    
    public FakultetSimpleDTO updateSimple(Long id, FakultetSimpleDTO dto) {
        Fakultet entity = fakultetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fakultet sa ID " + id + " nije pronađen."));

        entity.setNaziv(dto.getNaziv());
        entity.setEmail(dto.getEmail());
        entity.setOpis(dto.getOpis());
        entity.setLokacija(dto.getLokacija());
        entity.setBrojTelefona(dto.getBrojTelefona());

        if (dto.getDekanId() != null) {
            Profesor dekan = profesorRepository.findById(dto.getDekanId())
                    .orElseThrow(() -> new EntityNotFoundException("Dekan nije pronađen."));
            entity.setDekan(dekan);
        }

        return toSimpleDTO(fakultetRepository.save(entity));
    }
    
    @Override
    public FakultetSimpleDTO createSimple(FakultetSimpleDTO dto) {
        Fakultet entity = new Fakultet();

        entity.setNaziv(dto.getNaziv());
        entity.setEmail(dto.getEmail());
        entity.setOpis(dto.getOpis());
        entity.setLokacija(dto.getLokacija());
        entity.setBrojTelefona(dto.getBrojTelefona());

        if (dto.getDekanId() != null) {
            Profesor dekan = profesorRepository.findById(dto.getDekanId())
                    .orElseThrow(() -> new EntityNotFoundException("Dekan nije pronađen."));
            entity.setDekan(dekan);
        }

        // Ako univerzitet ne može biti null u bazi, postavi neki podrazumevani univerzitet
        Univerzitet podrazumevaniUniverzitet = univerzitetRepository.findById((long) 1)
                .orElseThrow(() -> new EntityNotFoundException("Univerzitet nije pronađen."));
        entity.setUniverzitet(podrazumevaniUniverzitet);

        return toSimpleDTO(fakultetRepository.save(entity));
    }



    @Override
    public void delete(Long id) {
        if (!fakultetRepository.existsById(id)) {
            throw new EntityNotFoundException("Fakultet sa ID " + id + " ne postoji.");
        }
        fakultetRepository.deleteById(id);
    }

    @Override
    public FakultetDTO toDTO(Fakultet entity) {
        FakultetDTO dto = new FakultetDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setEmail(entity.getEmail());
        dto.setOpis(entity.getOpis());
        dto.setLokacija(entity.getLokacija());
        dto.setBrojTelefona(entity.getBrojTelefona());

        if (entity.getUniverzitet() != null) {
            dto.setUniverzitet(univerzitetService.toDTO(entity.getUniverzitet()));
        }

        if (entity.getDekan() != null) {
            dto.setDekan(profesorService.toDTO(entity.getDekan()));
        }

        if (entity.getDepartmani() != null) {
            List<DepartmanDTO> departmaniDTO = entity.getDepartmani()
                    .stream()
                    .map(departmanService::toDTO)
                    .collect(Collectors.toList());
            dto.setDepartmani(departmaniDTO);
        }

        return dto;
    }

    @Override
    public Fakultet toEntity(FakultetDTO dto) {
        Fakultet entity = new Fakultet();
        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        entity.setEmail(dto.getEmail());
        entity.setOpis(dto.getOpis());
        entity.setLokacija(dto.getLokacija());
        entity.setBrojTelefona(dto.getBrojTelefona());

        if (dto.getUniverzitet() != null && dto.getUniverzitet().getId() != null) {
            entity.setUniverzitet(univerzitetService.toEntity(dto.getUniverzitet()));
        }

        if (dto.getDekan() != null && dto.getDekan().getId() != null) {
            entity.setDekan(profesorService.toEntity(dto.getDekan()));
        }

        if (dto.getDepartmani() != null) {
            List<Departman> departmani = dto.getDepartmani()
                    .stream()
                    .map(departmanService::toEntity)
                    .collect(Collectors.toCollection(ArrayList::new));
            entity.setDepartmani(new ArrayList<>(departmani));
        }

        return entity;
    }
//    @Override
//    public List<FakultetSimpleDTO> findAllSimple() {
//        return fakultetRepository.findAll()
//                .stream()
//                .map(this::toSimpleDTO)
//                .collect(Collectors.toList());
//    }
    
    @Override
    public List<FakultetSimpleDTO> findAllSimple() {
        return fakultetRepository.findByDeletedFalse()
                .stream()
                .map(this::toSimpleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FakultetSimpleDTO> findAllSimpleAdmin() {
        return fakultetRepository.findAllSortedByDeleted()
                .stream()
                .map(this::toSimpleDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public void setDeleted(Long id, boolean deleted) {
        Fakultet fakultet = fakultetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fakultet nije pronađen sa ID: " + id));
        fakultet.setDeleted(deleted);
        fakultetRepository.save(fakultet);
    }


    
    @Override
    public FakultetSimpleDTO findSimpleById(Long id) {
        Fakultet fakultet = fakultetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fakultet nije pronađen sa ID: " + id));
        return toSimpleDTO(fakultet);
    }


    @Override
    public FakultetSimpleDTO toSimpleDTO(Fakultet entity) {
        FakultetSimpleDTO dto = new FakultetSimpleDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setEmail(entity.getEmail());
        dto.setOpis(entity.getOpis());
        dto.setLokacija(entity.getLokacija());
        dto.setBrojTelefona(entity.getBrojTelefona());
        
        dto.setDeleted(entity.isDeleted());

        if (entity.getDekan() != null) {
        	dto.setDekanId(entity.getDekan().getUser().getId());
        	dto.setDekanIme(entity.getDekan().getUser().getIme());
        	dto.setDekanPrezime(entity.getDekan().getUser().getPrezime());
        	
        	dto.setDekanSlika(entity.getDekan().getSlikaPath());
            dto.setDekanOpis(entity.getDekan().getBiografija());
        }

        return dto;
    }

}
