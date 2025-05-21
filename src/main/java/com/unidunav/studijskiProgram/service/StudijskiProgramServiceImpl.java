package com.unidunav.studijskiProgram.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import com.unidunav.profesor.dto.ProfesorDTO;
import com.unidunav.profesor.model.Profesor;
import com.unidunav.profesor.repository.ProfesorRepository;
import com.unidunav.studijskiProgram.dto.StudijskiProgramDTO;
import com.unidunav.studijskiProgram.model.StudijskiProgram;
import com.unidunav.studijskiProgram.repository.StudijskiProgramRepository;
import com.unidunav.tipStudija.dto.TipStudijaDTO;
import com.unidunav.tipStudija.model.TipStudija;
import com.unidunav.tipStudija.repository.TipStudijaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudijskiProgramServiceImpl implements StudijskiProgramService {

    private final StudijskiProgramRepository studijskiProgramRepository;
    private final TipStudijaRepository tipStudijaRepository;
    private final ProfesorRepository profesorRepository;

    public StudijskiProgramServiceImpl(StudijskiProgramRepository studijskiProgramRepository,
                                       TipStudijaRepository tipStudijaRepository,
                                       ProfesorRepository profesorRepository) {
        this.studijskiProgramRepository = studijskiProgramRepository;
        this.tipStudijaRepository = tipStudijaRepository;
        this.profesorRepository = profesorRepository;
    }

    @Override
    public StudijskiProgramDTO create(StudijskiProgramDTO dto) {
        StudijskiProgram entity = toEntity(dto);
        entity = studijskiProgramRepository.save(entity);
        return toDTO(entity);
    }

    @Override
    public List<StudijskiProgramDTO> findAll() {
        return studijskiProgramRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudijskiProgramDTO findById(Long id) {
        StudijskiProgram entity = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Studijski program sa ID " + id + " nije pronađen."));
        return toDTO(entity);
    }

    @Override
    public StudijskiProgramDTO update(Long id, StudijskiProgramDTO dto) {
        StudijskiProgram entity = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Studijski program sa ID " + id + " nije pronađen."));

        entity.setNaziv(dto.getNaziv());
        entity.setOpis(dto.getOpis());

        TipStudija tipStudija = tipStudijaRepository.findById(dto.getTipStudija().getId())
                .orElseThrow(() -> new EntityNotFoundException("Tip studija sa ID " + dto.getTipStudija().getId() + " nije pronađen."));
        entity.setTipStudija(tipStudija);

        Profesor rukovodilac = profesorRepository.findById(dto.getRukovodilac().getId())
                .orElseThrow(() -> new EntityNotFoundException("Profesor rukovodilac sa ID " + dto.getRukovodilac().getId() + " nije pronađen."));
        entity.setRukovodilac(rukovodilac);

        entity = studijskiProgramRepository.save(entity);
        return toDTO(entity);
    }

    @Override
    public void delete(Long id) {
        if (!studijskiProgramRepository.existsById(id)) {
            throw new EntityNotFoundException("Studijski program sa ID " + id + " ne postoji.");
        }
        studijskiProgramRepository.deleteById(id);
    }

    private StudijskiProgramDTO toDTO(StudijskiProgram entity) {
        StudijskiProgramDTO dto = new StudijskiProgramDTO();

        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setOpis(entity.getOpis());

        if (entity.getTipStudija() != null) {
            TipStudijaDTO tipDto = new TipStudijaDTO();
            tipDto.setId(entity.getTipStudija().getId());
            tipDto.setTip(entity.getTipStudija().getTip());
            dto.setTipStudija(tipDto);
        }

        if (entity.getRukovodilac() != null) {
            ProfesorDTO profDto = new ProfesorDTO();
            profDto.setId(entity.getRukovodilac().getId());
            profDto.setIme(entity.getRukovodilac().getIme());
            profDto.setPrezime(entity.getRukovodilac().getPrezime());
            dto.setRukovodilac(profDto);
        }

        return dto;
    }

    private StudijskiProgram toEntity(StudijskiProgramDTO dto) {
        StudijskiProgram entity = new StudijskiProgram();

        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        entity.setOpis(dto.getOpis());

        TipStudija tipStudija = tipStudijaRepository.findById(dto.getTipStudija().getId())
                .orElseThrow(() -> new EntityNotFoundException("Tip studija sa ID " + dto.getTipStudija().getId() + " nije pronađen."));
        entity.setTipStudija(tipStudija);

        Profesor rukovodilac = profesorRepository.findById(dto.getRukovodilac().getId())
                .orElseThrow(() -> new EntityNotFoundException("Profesor rukovodilac sa ID " + dto.getRukovodilac().getId() + " nije pronađen."));
        entity.setRukovodilac(rukovodilac);

        return entity;
    }
}
