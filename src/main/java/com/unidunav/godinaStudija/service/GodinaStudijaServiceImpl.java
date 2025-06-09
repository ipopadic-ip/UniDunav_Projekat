package com.unidunav.godinaStudija.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unidunav.godinaStudija.dto.GodinaStudijaCreateUpdateDTO;
import com.unidunav.godinaStudija.dto.GodinaStudijaDTO;
import com.unidunav.godinaStudija.dto.GodinaStudijaReadDTO;
import com.unidunav.godinaStudija.model.GodinaStudija;
import com.unidunav.godinaStudija.repository.GodinaStudijaRepository;
import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.model.Predmet;
import com.unidunav.predmet.repository.PredmetRepository;
import com.unidunav.predmet.service.PredmetService;
import com.unidunav.studijskiProgram.dto.StudijskiProgramDTO;
import com.unidunav.studijskiProgram.model.StudijskiProgram;
import com.unidunav.studijskiProgram.repository.StudijskiProgramRepository;
import com.unidunav.studijskiProgram.service.StudijskiProgramService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GodinaStudijaServiceImpl implements GodinaStudijaService {

    private final GodinaStudijaRepository godinaStudijaRepository;
    private final StudijskiProgramRepository studijskiProgramRepository;
    private final PredmetRepository predmetRepository;
    private final StudijskiProgramService studijskiProgramService;
    private final PredmetService predmetService;

    public GodinaStudijaServiceImpl(GodinaStudijaRepository godinaStudijaRepository,
                                    StudijskiProgramRepository studijskiProgramRepository,
                                    PredmetRepository predmetRepository,
                                    StudijskiProgramService studijskiProgramService,
                                    PredmetService predmetService) {
        this.godinaStudijaRepository = godinaStudijaRepository;
        this.studijskiProgramRepository = studijskiProgramRepository;
        this.predmetRepository = predmetRepository;
        this.studijskiProgramService = studijskiProgramService;
        this.predmetService = predmetService;
    }
    
    @Override
    public GodinaStudijaReadDTO create(GodinaStudijaCreateUpdateDTO dto) {
        StudijskiProgram program = studijskiProgramRepository.findById(dto.getStudijskiProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Studijski program nije pronađen."));

        GodinaStudija godinaStudija = new GodinaStudija();
        godinaStudija.setGodina(dto.getGodina());
        godinaStudija.setStudijskiProgram(program);

        godinaStudija = godinaStudijaRepository.save(godinaStudija);
        return toReadDTO(godinaStudija);
    }

    @Override
    public GodinaStudijaReadDTO update(Long id, GodinaStudijaCreateUpdateDTO dto) {
        GodinaStudija godinaStudija = godinaStudijaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Godina studija nije pronađena."));

        StudijskiProgram program = studijskiProgramRepository.findById(dto.getStudijskiProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Studijski program nije pronađen."));

        godinaStudija.setGodina(dto.getGodina());
        godinaStudija.setStudijskiProgram(program);

        godinaStudija = godinaStudijaRepository.save(godinaStudija);
        return toReadDTO(godinaStudija);
    }

    @Override
    public List<GodinaStudijaReadDTO> findAll() {
        return godinaStudijaRepository.findAll().stream()
                .map(this::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GodinaStudijaReadDTO findById(Long id) {
        GodinaStudija godinaStudija = godinaStudijaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Godina studija nije pronađena."));
        return toReadDTO(godinaStudija);
    }
    
    public List<GodinaStudijaReadDTO> findAllAktivne(Optional<String> nazivPrograma) {
        List<GodinaStudija> result = nazivPrograma.isPresent()
            ? godinaStudijaRepository.findByDeletedFalseAndStudijskiProgramNazivContainingIgnoreCase(nazivPrograma.get())
            : godinaStudijaRepository.findByDeletedFalse();

        return result.stream().map(this::toReadDTO).collect(Collectors.toList());
    }

    public Map<String, List<GodinaStudijaReadDTO>> findAllZaAdmin(Optional<String> nazivPrograma) {
        List<GodinaStudija> aktivne = nazivPrograma.isPresent()
            ? godinaStudijaRepository.findByDeletedFalseAndStudijskiProgramNazivContainingIgnoreCase(nazivPrograma.get())
            : godinaStudijaRepository.findByDeletedFalse();

        List<GodinaStudija> obrisane = nazivPrograma.isPresent()
            ? godinaStudijaRepository.findByDeletedTrueAndStudijskiProgramNazivContainingIgnoreCase(nazivPrograma.get())
            : godinaStudijaRepository.findByDeletedTrue();

        Map<String, List<GodinaStudijaReadDTO>> rezultat = new HashMap<>();
        rezultat.put("aktivne", aktivne.stream().map(this::toReadDTO).collect(Collectors.toList()));
        rezultat.put("obrisane", obrisane.stream().map(this::toReadDTO).collect(Collectors.toList()));

        return rezultat;
    }

    public GodinaStudijaReadDTO deaktiviraj(Long id) {
        GodinaStudija entity = godinaStudijaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Godina studija nije pronađena."));
        entity.setDeleted(true);
        godinaStudijaRepository.save(entity);
        return toReadDTO(entity);
    }

    public GodinaStudijaReadDTO aktiviraj(Long id) {
        GodinaStudija entity = godinaStudijaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Godina studija nije pronađena."));
        entity.setDeleted(false);
        godinaStudijaRepository.save(entity);
        return toReadDTO(entity);
    }


    private GodinaStudijaReadDTO toReadDTO(GodinaStudija entity) {
        return new GodinaStudijaReadDTO(
                entity.getId(),
                entity.getGodina(),
                entity.getStudijskiProgram().getId(),
                entity.getStudijskiProgram().getNaziv(),
                entity.isDeleted()
        );
    }


//    @Override
//    public GodinaStudijaDTO create(GodinaStudijaDTO dto) {
//        GodinaStudija entity = toEntity(dto);
//        entity = godinaStudijaRepository.save(entity);
//        return toDTO(entity);
//    }
//
//    @Override
//    public List<GodinaStudijaDTO> findAll() {
//        return godinaStudijaRepository.findAll()
//                .stream()
//                .map(this::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public GodinaStudijaDTO findById(Long id) {
//        GodinaStudija entity = godinaStudijaRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Godina studija sa ID " + id + " nije pronađena."));
//        return toDTO(entity);
//    }
//
//    @Override
//    public GodinaStudijaDTO update(Long id, GodinaStudijaDTO dto) {
//        GodinaStudija entity = godinaStudijaRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Godina studija sa ID " + id + " nije pronađena."));
//
//        entity.setGodina(dto.getGodina());
//
//        StudijskiProgram sp = studijskiProgramRepository.findById(dto.getStudijskiProgram().getId())
//                .orElseThrow(() -> new EntityNotFoundException("Studijski program sa ID " + dto.getStudijskiProgram().getId() + " nije pronađen."));
//        entity.setStudijskiProgram(sp);
//
//        List<Long> predmetIds = dto.getPredmeti().stream()
//                .map(PredmetDTO::getId)
//                .collect(Collectors.toList());
//
//        List<Predmet> predmeti = predmetRepository.findAllById(predmetIds);
//        entity.setPredmeti(new ArrayList<>(predmeti));
//
//        entity = godinaStudijaRepository.save(entity);
//        return toDTO(entity);
//    }

//    @Override
//    public void delete(Long id) {
//        if (!godinaStudijaRepository.existsById(id)) {
//            throw new EntityNotFoundException("Godina studija sa ID " + id + " ne postoji.");
//        }
//        godinaStudijaRepository.deleteById(id);
//    }

    // -------------------- DTO KONVERZIJA --------------------

    private GodinaStudijaDTO toDTO(GodinaStudija entity) {
        StudijskiProgramDTO studijskiProgramDTO = studijskiProgramService.findById(entity.getStudijskiProgram().getId());

        List<PredmetDTO> predmetiDTO = entity.getPredmeti().stream()
                .map(predmet -> predmetService.findById(predmet.getId()))
                .collect(Collectors.toList());

        return new GodinaStudijaDTO(
                entity.getId(),
                entity.getGodina(),
                studijskiProgramDTO,
                predmetiDTO
        );
    }

    private GodinaStudija toEntity(GodinaStudijaDTO dto) {
        StudijskiProgram studijskiProgram = studijskiProgramRepository.findById(dto.getStudijskiProgram().getId())
                .orElseThrow(() -> new EntityNotFoundException("Studijski program sa ID " + dto.getStudijskiProgram().getId() + " nije pronađen."));

        List<Long> predmetIds = dto.getPredmeti().stream()
                .map(PredmetDTO::getId)
                .collect(Collectors.toList());

        List<Predmet> predmeti = predmetRepository.findAllById(predmetIds);

        return new GodinaStudija(
                dto.getId(),
                dto.getGodina(),
                studijskiProgram,
                new ArrayList<>(predmeti)
        );
    }
}
