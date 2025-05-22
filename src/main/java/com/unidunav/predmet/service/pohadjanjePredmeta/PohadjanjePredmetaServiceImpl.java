package com.unidunav.predmet.service.pohadjanjePredmeta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidunav.predmet.dto.PohadjanjePredmetaDTO;
import com.unidunav.predmet.model.PohadjanjePredmeta;
import com.unidunav.predmet.model.Predmet;
import com.unidunav.predmet.repository.PohadjanjePredmetaRepository;
import com.unidunav.predmet.repository.PredmetRepository;
import com.unidunav.student.model.Student;
import com.unidunav.student.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PohadjanjePredmetaServiceImpl implements PohadjanjePredmetaService {

    @Autowired
    private PohadjanjePredmetaRepository repository;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private PredmetRepository predmetRepo;

    private PohadjanjePredmetaDTO toDTO(PohadjanjePredmeta entity) {
        PohadjanjePredmetaDTO dto = new PohadjanjePredmetaDTO();
        dto.setId(entity.getId());
        dto.setOcena(entity.getOcena());
        dto.setBrojPolaganja(entity.getBrojPolaganja());
        dto.setAktivan(entity.isAktivan());
        dto.setDatumPocetka(entity.getDatumPocetka());
        dto.setDatumZavrsetka(entity.getDatumZavrsetka());
        dto.setStudentId(entity.getStudent().getId());
        dto.setPredmetId(entity.getPredmet().getId());
        return dto;
    }

    private PohadjanjePredmeta toEntity(PohadjanjePredmetaDTO dto) {
        PohadjanjePredmeta entity = new PohadjanjePredmeta();
        entity.setOcena(dto.getOcena());
        entity.setBrojPolaganja(dto.getBrojPolaganja());
        entity.setAktivan(dto.isAktivan());
        entity.setDatumPocetka(dto.getDatumPocetka());
        entity.setDatumZavrsetka(dto.getDatumZavrsetka());

        Student student = studentRepo.findById(dto.getStudentId()).orElseThrow();
        Predmet predmet = predmetRepo.findById(dto.getPredmetId()).orElseThrow();

        entity.setStudent(student);
        entity.setPredmet(predmet);
        return entity;
    }

    @Override
    public PohadjanjePredmetaDTO create(PohadjanjePredmetaDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<PohadjanjePredmetaDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PohadjanjePredmetaDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public PohadjanjePredmetaDTO update(Long id, PohadjanjePredmetaDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setOcena(dto.getOcena());
            existing.setBrojPolaganja(dto.getBrojPolaganja());
            existing.setAktivan(dto.isAktivan());
            existing.setDatumPocetka(dto.getDatumPocetka());
            existing.setDatumZavrsetka(dto.getDatumZavrsetka());
            existing.setStudent(studentRepo.findById(dto.getStudentId()).orElseThrow());
            existing.setPredmet(predmetRepo.findById(dto.getPredmetId()).orElseThrow());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
