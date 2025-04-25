package com.unidunav.administrator.service;

import com.unidunav.administrator.dto.AdministratorDTO;
import com.unidunav.administrator.model.Administrator;
import com.unidunav.administrator.repository.AdministratorRepository;
import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.model.Role;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository repository;
    
    @Autowired
    private UserRepository userRepository;

    private AdministratorDTO toDTO(Administrator admin) {
        AdministratorDTO dto = new AdministratorDTO();
        dto.setId(admin.getId());
        dto.setIme(admin.getIme());
        dto.setPrezime(admin.getPrezime());
        return dto;
    }

    private Administrator toEntity(AdministratorDTO dto) {
        Administrator admin = new Administrator();
        admin.setId(dto.getId());
        admin.setIme(dto.getIme());
        admin.setPrezime(dto.getPrezime());
        return admin;
    }

    @Override
    public AdministratorDTO create(AdministratorDTO dto) {
        Administrator saved = repository.save(toEntity(dto));
        return toDTO(saved);
    }

    @Override
    public List<AdministratorDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public AdministratorDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public AdministratorDTO update(Long id, AdministratorDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setIme(dto.getIme());
            existing.setPrezime(dto.getPrezime());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    private UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles()); // Ako DTO ima setRoles
        return dto;
    }
    
    @Override
    public UserDTO addRoleToUser(Long userId, Role role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));
        user.getRoles().add(role);
        userRepository.save(user);
        return toUserDTO(user);
    }

    @Override
    public UserDTO removeRoleFromUser(Long userId, Role role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));
        user.getRoles().remove(role);
        userRepository.save(user);
        return toUserDTO(user);
    }
}
