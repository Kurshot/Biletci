package Biletci.service;

import Biletci.dto.RegisterDTO;
import Biletci.dto.UserDTO;
import Biletci.enums.ResultMapping;
import Biletci.enums.UserRole;
import Biletci.mapper.UserMapper;
import Biletci.model.User;
import Biletci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.html.HTMLTableCaptionElement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get By ID
    public UserDTO getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserDTO userDTO = userMapper.toDTO(userOptional.get());
            return userDTO;
        }
        else{
            return null;
        }
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
        return userDTOs;
    }

    // Create User
    public UserDTO createUser(UserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.getEmail())) {
             throw new ResponseStatusException(HttpStatusCode.valueOf(410), "Email already exists.");
        }
        userDTO.setCreateDate(LocalDateTime.now());
        User user = userMapper.toEntity(userDTO);
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateDate(LocalDateTime.now());
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    // Update User

    public UserDTO updateUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getId())) {
            userDTO.setRole(UserRole.USER); // Admin olarak değiştirilememesi için.
            User user = userMapper.toEntity(userDTO);
            user = userRepository.save(user);
            return userMapper.toDTO(user);
        } else {
            return null;
        }
    }

    // Delete User ( Active / Passive )
    public boolean deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent())
        {
            User user = userOptional.get();
            user.setActive(false);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
