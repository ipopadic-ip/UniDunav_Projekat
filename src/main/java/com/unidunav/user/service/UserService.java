package com.unidunav.user.service;

import com.unidunav.user.dto.CreateUserDTO;
import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    public UserDTO createUser(CreateUserDTO dto) {
//        User user = new User();
//        user.setEmail(dto.getEmail());
//        user.setPassword(passwordEncoder.encode(dto.getPassword()));
//        user.setRoles(dto.getRoles());
//        User saved = userRepository.save(user);
//        return mapToDTO(saved);
//    }
    
    public UserDTO createUser(CreateUserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(dto.getRoles());
        
        User saved = userRepository.save(user);
        return mapToDTO(saved);
    }
    
    public User createUser1(CreateUserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(dto.getRoles());
        return userRepository.save(user); // VRATI sačuvanog user-a sa ID-jem
    }
    
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }




    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }
    
    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email)
                             .map(this::mapToDTO);
    }
    
    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) return false;

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false; // stara lozinka nije tačna
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

}
