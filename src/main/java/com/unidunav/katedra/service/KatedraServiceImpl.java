package com.unidunav.katedra.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidunav.katedra.dto.KatedraCreateUpdateDTO;
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
import com.unidunav.profesor.service.ProfesorService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class KatedraServiceImpl implements KatedraService {

    private final KatedraRepository katedraRepository;
    private final DepartmanRepository departmanRepository;
    private final TipStudijaRepository tipStudijaRepository;
    private final ProfesorRepository profesorRepository;
    private final PredmetService predmetService;
    
    @Autowired
    private ProfesorService profesorService;

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
    public List<KatedraDTO> findByDepartmanId(Long departmanId) {
        return katedraRepository.findByDepartmanId(departmanId)
                .stream()
                .map(this::toDTO)
                .toList();
    }
    
    @Override
    public List<KatedraDTO> findAllAktivne() {
        return katedraRepository.findByDeletedFalse()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<KatedraDTO> findAllAdmin() {
        return katedraRepository.findAllSortiranoPoAktivnostiIFakultetu()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<KatedraDTO> findAktivneByDepartmanId(Long departmanId) {
        return katedraRepository.findByDepartmanIdAndDeletedFalse(departmanId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void setDeleted(Long id, boolean deleted) {
        Katedra entity = katedraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Katedra nije pronađena"));
        entity.setDeleted(deleted);
        katedraRepository.save(entity);
    }
    
    @Override
    public KatedraDTO create(KatedraCreateUpdateDTO dto) {
        Katedra katedra = new Katedra();
        katedra.setNaziv(dto.getNaziv());
        katedra.setOpis(dto.getOpis());
        katedra.setDeleted(false);

        Profesor sef = profesorRepository.findById(dto.getSefKatedreId())
                .orElseThrow(() -> new EntityNotFoundException("Profesor nije pronađen"));
        katedra.setSefKatedre(sef);

        Departman departman = departmanRepository.findById(dto.getDepartmanId())
                .orElseThrow(() -> new EntityNotFoundException("Departman nije pronađen"));
        katedra.setDepartman(departman);

        return toDTO(katedraRepository.save(katedra));
    }

    @Override
    public KatedraDTO update(Long id, KatedraCreateUpdateDTO dto) {
        Katedra katedra = katedraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Katedra nije pronađena"));

        katedra.setNaziv(dto.getNaziv());
        katedra.setOpis(dto.getOpis());

        Profesor sef = profesorRepository.findById(dto.getSefKatedreId())
                .orElseThrow(() -> new EntityNotFoundException("Profesor nije pronađen"));
        katedra.setSefKatedre(sef);

        Departman departman = departmanRepository.findById(dto.getDepartmanId())
                .orElseThrow(() -> new EntityNotFoundException("Departman nije pronađen"));
        katedra.setDepartman(departman);

        return toDTO(katedraRepository.save(katedra));
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
        dto.setDeleted(entity.isDeleted());

        // Departman
        if (entity.getDepartman() != null) {
            var d = entity.getDepartman();
            var departmanDTO = new com.unidunav.dapartman.dto.DepartmanDTO();
            departmanDTO.setId(d.getId());
            departmanDTO.setNaziv(d.getNaziv());
            dto.setDepartman(departmanDTO);
        }

        
     // Tipovi studija – prikaz samo aktivnih
        if (entity.getTipoviStudija() != null) {
            List<com.unidunav.tipStudija.dto.TipStudijaDTO> tipDtoList = entity.getTipoviStudija().stream()
                    .filter(t -> !t.isDeleted()) // <-- filtriramo samo aktivne
                    .map(t -> {
                        var tipDto = new com.unidunav.tipStudija.dto.TipStudijaDTO();
                        tipDto.setId(t.getId());
                        tipDto.setTip(t.getTip());
                        return tipDto;
                    })
                    .collect(Collectors.toList());
            dto.setTipoviStudija(tipDtoList);
        }

//        if (entity.getTipoviStudija() != null) {
//            List<com.unidunav.tipStudija.dto.TipStudijaDTO> tipDtoList = entity.getTipoviStudija().stream()
//                    .map(t -> {
//                        var tipDto = new com.unidunav.tipStudija.dto.TipStudijaDTO();
//                        tipDto.setId(t.getId());
//                        tipDto.setTip(t.getTip());
//                        return tipDto;
//                    }).collect(Collectors.toList());
//            dto.setTipoviStudija(tipDtoList);
//        }

        // Šef katedre
        if (entity.getSefKatedre() != null) {
       	 dto.setSefKatedre(profesorService.toDTO(entity.getSefKatedre()));
       }
        
        return dto;
    }

    private Katedra toEntity(KatedraDTO dto) {
        Katedra entity = new Katedra();

        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        entity.setOpis(dto.getOpis());
        entity.setDeleted(dto.isDeleted());

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

        return entity;
    }
}
