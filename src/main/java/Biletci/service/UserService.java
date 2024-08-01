package Biletci.service;

import Biletci.dto.UserDTO;
import Biletci.enums.ResultMapping;
import Biletci.mapper.UserMapper;
import Biletci.model.User;
import Biletci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

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
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    // Update User
    // TODO : Update için validate yazılacak ki admin olarak degistirilemesin.
    public UserDTO updateUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getId())) {
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
