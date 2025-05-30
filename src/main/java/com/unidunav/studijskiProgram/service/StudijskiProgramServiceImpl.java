package com.unidunav.studijskiProgram.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidunav.godinaStudija.model.GodinaStudija;
import com.unidunav.godinaStudija.repository.GodinaStudijaRepository;
import com.unidunav.katedra.Repository.KatedraRepository;
import com.unidunav.katedra.model.Katedra;
import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.model.Predmet;
import com.unidunav.predmet.repository.PredmetRepository;
import com.unidunav.profesor.dto.ProfesorDTO;
import com.unidunav.profesor.model.Profesor;
import com.unidunav.profesor.repository.ProfesorRepository;
import com.unidunav.profesor.service.ProfesorService;
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
    
    private final KatedraRepository katedraRepository;
    
    @Autowired
    private PredmetRepository predmetRepository;
    
    @Autowired
    private GodinaStudijaRepository godinaStudijaRepository;
    
    @Autowired
    private ProfesorService profesorService;

    public StudijskiProgramServiceImpl(StudijskiProgramRepository studijskiProgramRepository,
                                       TipStudijaRepository tipStudijaRepository,
                                       ProfesorRepository profesorRepository, KatedraRepository katedraRepository) {
        this.studijskiProgramRepository = studijskiProgramRepository;
        this.tipStudijaRepository = tipStudijaRepository;
        this.profesorRepository = profesorRepository;
        this.katedraRepository = katedraRepository;
    }

    @Override
    public StudijskiProgramDTO create(StudijskiProgramDTO dto) {
        StudijskiProgram entity = toEntity(dto);
        entity = studijskiProgramRepository.save(entity);
        return toDTO(entity);
    }
    
    @Override
    public Map<String, List<StudijskiProgramDTO>> findByKatedraGroupedByTipStudija(Long katedraId) {
        List<StudijskiProgram> programi = studijskiProgramRepository.findByKatedraId(katedraId);
        
        return programi.stream()
            .map(this::toDTO)
            .collect(Collectors.groupingBy(dto -> dto.getTipStudija().getTip()));
    }
    
//    @Override
//    public Map<Integer, List<PredmetDTO>> getPredmetiPoGodinama(Long studijskiProgramId) {
//        StudijskiProgram sp = studijskiProgramRepository.findById(studijskiProgramId)
//            .orElseThrow(() -> new RuntimeException("Nema takvog programa"));
//
//        Map<Integer, List<PredmetDTO>> result = new TreeMap<>();
//        for (GodinaStudija g : sp.getGodineStudija()) {
//            List<PredmetDTO> predmeti = g.getPredmeti().stream()
//                .map(p -> new PredmetDTO(p.getId(), p.getNaziv(), p.getEcts(), p.getInformacijeOPredmetu(), studijskiProgramId))
//                .collect(Collectors.toList());
//            result.put(g.getGodina(), predmeti);
//        }
//
//        return result;
//    }
    
    @Override
    public Map<Integer, List<PredmetDTO>> getPredmetiPoGodinama(Long studijskiProgramId) {
        List<GodinaStudija> godine = godinaStudijaRepository.findByStudijskiProgramId(studijskiProgramId);

        Map<Integer, List<PredmetDTO>> result = new TreeMap<>();

        for (GodinaStudija godina : godine) {
            List<Predmet> predmeti = predmetRepository.findByGodinaStudijaId(godina.getId());
            

            List<PredmetDTO> predmetiDTO = predmeti.stream()
                .map(p -> new PredmetDTO(
                    p.getId(),
                    p.getNaziv(),
                    p.getEcts(),
                    p.getInformacijeOPredmetu(),
                    godina.getId()
//                    studijskiProgramId
                ))
                .collect(Collectors.toList());

            result.computeIfAbsent(godina.getGodina(), k -> new ArrayList<>()).addAll(predmetiDTO);
        }

        return result;
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
        
        if (dto.getKatedraId() != null) {
            Katedra katedra = katedraRepository.findById(dto.getKatedraId())
                .orElseThrow(() -> new EntityNotFoundException("Katedra sa ID " + dto.getKatedraId() + " nije pronađena."));
            entity.setKatedra(katedra);
        }


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
        dto.setKatedraId(entity.getKatedra() != null ? entity.getKatedra().getId() : null);


        if (entity.getTipStudija() != null) {
            TipStudijaDTO tipDto = new TipStudijaDTO();
            tipDto.setId(entity.getTipStudija().getId());
            tipDto.setTip(entity.getTipStudija().getTip());
            dto.setTipStudija(tipDto);
        }
        
        if (entity.getRukovodilac() != null) {
          	 dto.setRukovodilac(profesorService.toDTO(entity.getRukovodilac()));
          }

//        if (entity.getRukovodilac() != null) {
//            ProfesorDTO profDto = new ProfesorDTO();
//            profDto.setId(entity.getRukovodilac().getId());
//            dto.setRukovodilac(profDto);
//        }

        return dto;
    }

    private StudijskiProgram toEntity(StudijskiProgramDTO dto) {
        StudijskiProgram entity = new StudijskiProgram();

        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        entity.setOpis(dto.getOpis());
        
        if (dto.getKatedraId() != null) {
            Katedra katedra = katedraRepository.findById(dto.getKatedraId())
                .orElseThrow(() -> new EntityNotFoundException("Katedra sa ID " + dto.getKatedraId() + " nije pronađena."));
            entity.setKatedra(katedra);
        }


        TipStudija tipStudija = tipStudijaRepository.findById(dto.getTipStudija().getId())
                .orElseThrow(() -> new EntityNotFoundException("Tip studija sa ID " + dto.getTipStudija().getId() + " nije pronađen."));
        entity.setTipStudija(tipStudija);

        Profesor rukovodilac = profesorRepository.findById(dto.getRukovodilac().getId())
                .orElseThrow(() -> new EntityNotFoundException("Profesor rukovodilac sa ID " + dto.getRukovodilac().getId() + " nije pronađen."));
        entity.setRukovodilac(rukovodilac);

        return entity;
    }
}
