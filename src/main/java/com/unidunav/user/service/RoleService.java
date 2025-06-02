package com.unidunav.user.service;

import com.unidunav.user.dto.RoleDTO;
import com.unidunav.user.model.Role;
import com.unidunav.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDTO> getAllActiveRoles() {
        return roleRepository.findByAktivnaTrue()
                .stream()
                .map(role -> new RoleDTO(role.getId(), role.getNaziv(), role.isAktivna()))
                .collect(Collectors.toList());
    }

    public RoleDTO createRole(RoleDTO roleDTO) {
    	Optional<Role> existingRole = roleRepository.findByNaziv(roleDTO.getNaziv());
	    if (existingRole.isPresent() && existingRole.get().isAktivna()) {
	        throw new RuntimeException("Rola sa tim nazivom već postoji i aktivna je.");
	    }
    	    
        Role role = new Role();
        role.setNaziv(roleDTO.getNaziv());
        role.setAktivna(true);
        Role saved = roleRepository.save(role);
        return new RoleDTO(saved.getId(), saved.getNaziv(), saved.isAktivna());
    }
    
    public List<RoleDTO> getAllRolesSorted() {
        return roleRepository.findAll().stream()
                .sorted((r1, r2) -> Boolean.compare(!r1.isAktivna(), !r2.isAktivna())) // aktivne prvo
                .map(role -> new RoleDTO(role.getId(), role.getNaziv(), role.isAktivna()))
                .collect(Collectors.toList());
    }

    public RoleDTO updateRole(Long id, RoleDTO updatedRoleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rola nije pronađena"));

        role.setNaziv(updatedRoleDTO.getNaziv());
        role.setAktivna(updatedRoleDTO.isAktivna());

        Role updated = roleRepository.save(role);
        return new RoleDTO(updated.getId(), updated.getNaziv(), updated.isAktivna());
    }


    public void deactivateRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rola nije pronađena"));
        
        if ("ADMIN".equals(role.getNaziv())) {
            throw new RuntimeException("ADMIN rola se ne može deaktivirati.");
        }
        role.setAktivna(false);
        roleRepository.save(role);
    }
    
    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rola nije pronađena"));
        return new RoleDTO(role.getId(), role.getNaziv(), role.isAktivna());
    }

    
    public void activateRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rola nije pronađena"));

        role.setAktivna(true);
        roleRepository.save(role);
    }

}
