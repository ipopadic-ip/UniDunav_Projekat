package com.unidunav.student.service;

import com.unidunav.obavestenje.dto.ObavestenjeStudentuDTO;
import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.model.PohadjanjePredmeta;
import com.unidunav.predmet.model.Predmet;
import com.unidunav.predmet.repository.PohadjanjePredmetaRepository;
import com.unidunav.student.dto.StudentDTO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;
import com.unidunav.student.model.Student;
import com.unidunav.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Transactional(readOnly = true)
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;
    
    
    @Autowired
    private PohadjanjePredmetaRepository pohadjanjePredmetaRepository;

    public List<PredmetDTO> getPredmetiKojeStudentSlusa(Long studentId) {
        List<PohadjanjePredmeta> pohadjanja = pohadjanjePredmetaRepository.findByStudentId(studentId);

        return pohadjanja.stream()
            .map(pp -> {
                Predmet predmet = pp.getPredmet();
                return new PredmetDTO(predmet.getId(), predmet.getNaziv(), predmet.getEcts(), predmet.getInformacijeOPredmetu());
            })
            .collect(Collectors.toList());
    }

    private StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
//        dto.setIme(student.getIme());
//        dto.setPrezime(student.getPrezime());
        dto.setBrojIndeksa(student.getBrojIndeksa());
        dto.setGodinaUpisa(student.getGodinaUpisa());
        dto.setProsecnaOcena(student.getProsecnaOcena());
        dto.setUkupnoEcts(student.getUkupnoEcts());
        
     // u toDTO
        dto.setSlikaPath(student.getSlikaPath());
        return dto;
    }

    private Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
//        student.setIme(dto.getIme());
//        student.setPrezime(dto.getPrezime());
        student.setBrojIndeksa(dto.getBrojIndeksa());
        student.setGodinaUpisa(dto.getGodinaUpisa());
        student.setProsecnaOcena(dto.getProsecnaOcena());
        student.setUkupnoEcts(dto.getUkupnoEcts());
        
     // u toEntity
        student.setSlikaPath(dto.getSlikaPath());
        return student;
    }
    
    @Autowired
    private PohadjanjePredmetaRepository pohadjanjePredmetaRepo;

    @Override
    public List<ObavestenjeStudentuDTO> getObavestenjaZaStudenta(Long studentId) {
        List<PohadjanjePredmeta> pohadjanja = pohadjanjePredmetaRepo.findByStudentId(studentId);

        return pohadjanja.stream()
            .filter(PohadjanjePredmeta::isAktivan)
            .map(PohadjanjePredmeta::getPredmet)
            .filter(predmet -> predmet.getObavestenja() != null && !predmet.getObavestenja().isEmpty())
            .flatMap(predmet ->
                predmet.getObavestenja().stream()
                    .map(obavestenje -> new ObavestenjeStudentuDTO(
                        predmet.getNaziv(),
                        obavestenje.getTekst(),
                        obavestenje.getDatum()
                    ))
            )
            .collect(Collectors.toList());
    }
    

    @Override
    public StudentDTO create(StudentDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<StudentDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public StudentDTO update(Long id, StudentDTO dto) {
        return repository.findById(id).map(existing -> {
//            existing.setIme(dto.getIme());
//            existing.setPrezime(dto.getPrezime());
            existing.setBrojIndeksa(dto.getBrojIndeksa());
            existing.setGodinaUpisa(dto.getGodinaUpisa());
            existing.setProsecnaOcena(dto.getProsecnaOcena());
            existing.setUkupnoEcts(dto.getUkupnoEcts());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public String uploadSlika(Long studentId, MultipartFile slika) throws IOException {
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student nije pronađen"));

        String folder = "uploads/studenti/";
        File dir = new File(folder);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalFilename = slika.getOriginalFilename();
        String filename = "student_" + studentId + "_" + System.currentTimeMillis() + "_" + originalFilename;
        Path filePath = Paths.get(folder + filename);

        Files.write(filePath, slika.getBytes());

        student.setSlikaPath(filePath.toString());
        repository.save(student);

        return filePath.toString();
    }
    public List<StudentDTO> findByBrojIndeksa(String indeks) {
        return repository.findByBrojIndeksaContainingIgnoreCase(indeks).stream().map(student -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(student.getId());
            dto.setIme(student.getUser().getIme());
            dto.setPrezime(student.getUser().getPrezime());
            dto.setBrojIndeksa(student.getBrojIndeksa());
            return dto;
        }).collect(Collectors.toList());
    }
}