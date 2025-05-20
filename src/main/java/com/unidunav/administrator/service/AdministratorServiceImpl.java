package com.unidunav.administrator.service;

import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.dto.request.UpdateRolesRequest;
import com.unidunav.user.model.Role;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.RoleRepository;
import com.unidunav.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
