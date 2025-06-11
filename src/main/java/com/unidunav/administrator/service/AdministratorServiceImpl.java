package com.unidunav.administrator.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.unidunav.administrator.dto.ProfesorXmlDTO;
import com.unidunav.administrator.dto.ProfesorXmlList;
import com.unidunav.administrator.dto.StudentXmlDTO;
import com.unidunav.administrator.dto.StudentXmlList;
import com.unidunav.profesor.model.Profesor;
import com.unidunav.profesor.repository.ProfesorRepository;
import com.unidunav.student.model.Student;
import com.unidunav.student.repository.StudentRepository;
import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.dto.request.UpdateRolesRequest;
import com.unidunav.user.model.Role;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.RoleRepository;
import com.unidunav.user.repository.UserRepository;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlElement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Override
    @Transactional
    public Resource exportStudentsToPdf() throws Exception {
        List<Student> students = studentRepository.findAllWithUser();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        document.add(new Paragraph("Spisak studenata", titleFont));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{2, 2, 2, 2, 2, 2});

        Stream.of("Ime", "Prezime", "Broj indeksa", "Godina upisa", "Prosek", "Ukupno ECTS")
                .forEach(title -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Phrase(title, headerFont));
                    table.addCell(header);
                });

        for (Student s : students) {
            User u = s.getUser();
            table.addCell(new Phrase(u.getIme(), bodyFont));
            table.addCell(new Phrase(u.getPrezime(), bodyFont));
            table.addCell(new Phrase(s.getBrojIndeksa(), bodyFont));
            table.addCell(new Phrase(String.valueOf(s.getGodinaUpisa()), bodyFont));
            table.addCell(new Phrase(String.format("%.2f", s.getProsecnaOcena()), bodyFont));
            table.addCell(new Phrase(String.valueOf(s.getUkupnoEcts()), bodyFont));
        }

        document.add(table);
        document.close();

        return new ByteArrayResource(out.toByteArray());
    }


    
    
    
    public Resource exportStudentsToXml() throws Exception {
        List<Student> students = studentRepository.findAll();
        List<StudentXmlDTO> xmlList = students.stream()
            .map(s -> new StudentXmlDTO(
                s.getUser().getIme(),
                s.getUser().getPrezime(),
                s.getBrojIndeksa(),
                s.getGodinaUpisa(),
                s.getProsecnaOcena(),
                s.getUkupnoEcts()
            )).toList();

        StudentXmlList wrapper = new StudentXmlList(xmlList);

        JAXBContext context = JAXBContext.newInstance(StudentXmlList.class, StudentXmlDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(wrapper, writer);

        return new ByteArrayResource(writer.toString().getBytes(StandardCharsets.UTF_8));
    }
    
    @Override
    @Transactional
    public Resource exportProfesorsToPdf() throws Exception {
        List<Profesor> profesori = profesorRepository.findByUserDeletedFalse(); // metoda treba da eager load User entitet

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        document.add(new Paragraph("Spisak profesora", titleFont));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 3, 5, 10}); // širine kolona

        Stream.of("Ime", "Prezime", "Email", "Biografija")
                .forEach(title -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Phrase(title, headerFont));
                    table.addCell(header);
                });

        for (Profesor p : profesori) {
            User u = p.getUser();
            table.addCell(new Phrase(u.getIme(), bodyFont));
            table.addCell(new Phrase(u.getPrezime(), bodyFont));
            table.addCell(new Phrase(u.getEmail(), bodyFont));
            table.addCell(new Phrase(p.getBiografija() != null ? p.getBiografija() : "", bodyFont));
        }

        document.add(table);
        document.close();

        return new ByteArrayResource(out.toByteArray());
    }

    @Override
    @Transactional
    public Resource exportProfesorsToXml() throws Exception {
        List<Profesor> profesori = profesorRepository.findByUserDeletedFalse();

        List<ProfesorXmlDTO> xmlList = profesori.stream()
            .map(p -> new ProfesorXmlDTO(
                p.getUser().getIme(),
                p.getUser().getPrezime(),
                p.getUser().getEmail(),
                p.getBiografija()
            ))
            .toList();

        ProfesorXmlList wrapper = new ProfesorXmlList(xmlList);

        JAXBContext context = JAXBContext.newInstance(ProfesorXmlList.class, ProfesorXmlDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(wrapper, writer);

        return new ByteArrayResource(writer.toString().getBytes(StandardCharsets.UTF_8));
    }



    @Override
    public UserDTO addRoleToUser(Long userId, String roleName) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        Role role = roleRepository.findByNaziv(roleName)
            .orElseThrow(() -> new RuntimeException("Rola ne postoji: " + roleName));

        user.getRoles().add(role);
        userRepository.save(user);

        return toUserDTO(user);
    }

    @Override
    public UserDTO removeRoleFromUser(Long userId, String roleName) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        Role role = roleRepository.findByNaziv(roleName)
            .orElseThrow(() -> new RuntimeException("Rola ne postoji: " + roleName));

        user.getRoles().remove(role);
        userRepository.save(user);

        return toUserDTO(user);
    }
    
    @Override
    public UserDTO updateUserRoles(Long userId, UpdateRolesRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        // Očisti postojeće uloge
        user.getRoles().clear();

        // Učitaj nove uloge
        Set<Role> updatedRoles = request.getRoles().stream()
                .map(name -> roleRepository.findByNaziv(name)
                        .orElseThrow(() -> new RuntimeException("Rola ne postoji: " + name)))
                .collect(Collectors.toSet());

        // Postavi nove
        user.getRoles().addAll(updatedRoles);

        // Sačuvaj
        userRepository.save(user);

        return toUserDTO(user);
    }

    private UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }
}
