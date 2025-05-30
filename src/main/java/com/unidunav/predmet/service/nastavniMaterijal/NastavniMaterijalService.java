package com.unidunav.predmet.service.nastavniMaterijal;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.unidunav.predmet.dto.NastavniMaterijalDTO;
import com.unidunav.predmet.model.NastavniMaterijal;
import com.unidunav.predmet.model.Predmet;
import com.unidunav.predmet.repository.NastavniMaterijalRepository;
import com.unidunav.predmet.repository.PredmetRepository;

@Service
public class NastavniMaterijalService {

    @Autowired
    private NastavniMaterijalRepository repository;

    @Autowired
    private PredmetRepository predmetRepository;
    
    private final String uploadFolder = "uploads/nastavni-materijali/";

    public NastavniMaterijalDTO upload(MultipartFile file, String naziv, Long predmetId) throws IOException {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));

        // Kreiraj folder ako ne postoji
        File folder = new File(uploadFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = "nastavni_" + predmetId + "_" + System.currentTimeMillis() + "_" + originalFilename;
        Path filePath = Paths.get(uploadFolder + uniqueFilename);

        // Snimi fajl
        Files.write(filePath, file.getBytes());

        // Kreiraj entitet i sačuvaj u bazu
        NastavniMaterijal materijal = new NastavniMaterijal();
        materijal.setNaziv(naziv);
        materijal.setFajlPutanja(filePath.toString());
        materijal.setPredmet(predmet);

        repository.save(materijal);

        return toDTO(materijal);
    }

    public List<NastavniMaterijalDTO> findByPredmet(Long predmetId) {
        return repository.findByPredmetId(predmetId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public void delete(Long id) {
        NastavniMaterijal materijal = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materijal nije pronađen"));

        // Obriši fajl sa diska
        Path filePath = Paths.get(materijal.getFajlPutanja());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Greška prilikom brisanja fajla", e);
        }

        // Obriši iz baze
        repository.delete(materijal);
    }

    public NastavniMaterijalDTO update(Long id, String naziv, MultipartFile file) throws IOException {
        NastavniMaterijal materijal = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materijal nije pronađen"));

        materijal.setNaziv(naziv);

        if (file != null && !file.isEmpty()) {
            // Obriši stari fajl
            Path stariFajl = Paths.get(materijal.getFajlPutanja());
            Files.deleteIfExists(stariFajl);

            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = "nastavni_" + materijal.getPredmet().getId() + "_" + System.currentTimeMillis() + "_" + originalFilename;
            Path noviFajl = Paths.get(uploadFolder + uniqueFilename);

            Files.write(noviFajl, file.getBytes());

            materijal.setFajlPutanja(noviFajl.toString());
        }

        return toDTO(repository.save(materijal));
    }


    public Resource preuzmiMaterijal(Long id) {
        NastavniMaterijal materijal = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materijal nije pronađen"));

        return new FileSystemResource(materijal.getFajlPutanja());
    }

    private NastavniMaterijalDTO toDTO(NastavniMaterijal materijal) {
        NastavniMaterijalDTO dto = new NastavniMaterijalDTO();
        dto.setId(materijal.getId());
        dto.setNaziv(materijal.getNaziv());
        dto.setFajlPutanja(materijal.getFajlPutanja());
        dto.setPredmetId(materijal.getPredmet().getId());
        return dto;
    }
}