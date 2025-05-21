package com.unidunav.univerzitet.service;

import java.util.List;
import java.util.stream.Collectors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.unidunav.fakultet.dto.FakultetDTO;
import com.unidunav.fakultet.model.Fakultet;
import com.unidunav.fakultet.repository.FakultetRepository;
import com.unidunav.profesor.dto.ProfesorDTO;
import com.unidunav.profesor.model.Profesor;
import com.unidunav.profesor.repository.ProfesorRepository;
import com.unidunav.univerzitet.dto.UniverzitetDTO;
import com.unidunav.univerzitet.model.Univerzitet;
import com.unidunav.univerzitet.repository.UniverzitetRepository;

@Service
public class UniverzitetServiceImpl implements UniverzitetService {

    private final UniverzitetRepository univerzitetRepository;
    private final FakultetRepository fakultetRepository;
    private final ProfesorRepository profesorRepository;

    public UniverzitetServiceImpl(UniverzitetRepository univerzitetRepository,
                                   FakultetRepository fakultetRepository,
                                   ProfesorRepository profesorRepository) {
        this.univerzitetRepository = univerzitetRepository;
        this.fakultetRepository = fakultetRepository;
        this.profesorRepository = profesorRepository;
    }

    @Override
    public UniverzitetDTO create(UniverzitetDTO dto) {
        Univerzitet univerzitet = toEntity(dto);
        univerzitet = univerzitetRepository.save(univerzitet);
        return toDTO(univerzitet);
    }

    @Override
    public List<UniverzitetDTO> findAll() {
        return univerzitetRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UniverzitetDTO findById(Long id) {
        Univerzitet univerzitet = univerzitetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Univerzitet nije pronađen"));
        return toDTO(univerzitet);
    }

    @Override
    public UniverzitetDTO update(Long id, UniverzitetDTO dto) {
        Univerzitet existing = univerzitetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Univerzitet nije pronađen"));

        existing.setNaziv(dto.getNaziv());
        existing.setEmail(dto.getEmail());
        existing.setBrojTelefona(dto.getBrojTelefona());
        existing.setOpis(dto.getOpis());

        if (dto.getRektor() != null && dto.getRektor().getId() != null) {
            Profesor rektor = profesorRepository.findById(dto.getRektor().getId())
                    .orElseThrow(() -> new RuntimeException("Rektor nije pronađen"));
            existing.setRektor(rektor);
        } else {
            existing.setRektor(null);
        }

        univerzitetRepository.save(existing);
        return toDTO(existing);
    }

    @Override
    public void delete(Long id) {
        univerzitetRepository.deleteById(id);
    }

    @Override
    public UniverzitetDTO toDTO(Univerzitet entity) {
        List<FakultetDTO> fakulteti = entity.getFakulteti() != null ?
                entity.getFakulteti().stream()
                        .map(f -> {
                            FakultetDTO dto = new FakultetDTO();
                            dto.setId(f.getId());
                            dto.setNaziv(f.getNaziv());
                            dto.setEmail(f.getEmail());
                            dto.setOpis(f.getOpis());

                            if (f.getDekan() != null) {
                                ProfesorDTO dekanDTO = new ProfesorDTO();
                                dekanDTO.setId(f.getDekan().getId());
                                dekanDTO.setIme(f.getDekan().getIme());
                                dekanDTO.setPrezime(f.getDekan().getPrezime());
                                dekanDTO.setBiografija(f.getDekan().getBiografija());
                                dto.setDekan(dekanDTO);
                            }

                            dto.setDepartmani(null);

                            dto.setUniverzitet(null);

                            return dto;
                        }).collect(Collectors.toList())
                : null;

        ProfesorDTO rektor = null;
        if (entity.getRektor() != null) {
            Profesor p = entity.getRektor();
            rektor = new ProfesorDTO();
            rektor.setId(p.getId());
            rektor.setIme(p.getIme());
            rektor.setPrezime(p.getPrezime());
            rektor.setBiografija(p.getBiografija());
        }

        return new UniverzitetDTO(
                entity.getId(),
                entity.getNaziv(),
                entity.getEmail(),
                entity.getBrojTelefona(),
                entity.getOpis(),
                fakulteti,
                rektor
        );
    }
    
    @Override
    public String uploadSlika(Long univerzitetId, MultipartFile slika) throws IOException {
        Univerzitet univerzitet = univerzitetRepository.findById(univerzitetId)
                .orElseThrow(() -> new RuntimeException("Univerzitet nije pronađen"));

        // Kreiranje foldera ako ne postoji
        String folder = "uploads/univerziteti/";
        File dir = new File(folder);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Ime fajla
        String originalFilename = slika.getOriginalFilename();
        String filename = "univerzitet_" + univerzitetId + "_" + System.currentTimeMillis() + "_" + originalFilename;
        Path filePath = Paths.get(folder + filename);

        // Snimanje slike na disk
        Files.write(filePath, slika.getBytes());

        // Snimanje putanje u bazu
        univerzitet.setSlikaPath(filePath.toString());
        univerzitetRepository.save(univerzitet);

        return filePath.toString();
    }

    @Override
    public Univerzitet toEntity(UniverzitetDTO dto) {
        List<Fakultet> fakulteti = null;
        if (dto.getFakulteti() != null) {
            fakulteti = dto.getFakulteti().stream()
                    .map(f -> fakultetRepository.findById(f.getId())
                            .orElseThrow(() -> new RuntimeException("Fakultet nije pronađen: " + f.getId())))
                    .collect(Collectors.toList());
        }

        Profesor rektor = null;
        if (dto.getRektor() != null && dto.getRektor().getId() != null) {
            rektor = profesorRepository.findById(dto.getRektor().getId())
                    .orElseThrow(() -> new RuntimeException("Rektor nije pronađen: " + dto.getRektor().getId()));
        }

        return new Univerzitet(
                dto.getId(),
                dto.getNaziv(),
                dto.getEmail(),
                dto.getBrojTelefona(),
                dto.getOpis(),
                fakulteti,
                rektor
        );
    }
}
