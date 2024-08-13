package Biletci.service;

import Biletci.dto.LoginDTO;
import Biletci.dto.UserDTO;
import Biletci.mapper.UserMapper;
import Biletci.model.User;
import Biletci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;


    public LoginDTO login(LoginDTO loginDTO) {
        if(!userRepository.existsByEmail(loginDTO.getEmail())){ // Email kayıtlı değil ise
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "User does not exist");
        }
        else{ // Email kayıtlı ise
            Optional<User> userOptional = userRepository.findByEmail(loginDTO.getEmail());
            if(!userOptional.get().getRole().name().equals("ADMIN")){
                throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Unauthorized");
            }
        }
        return loginDTO;
    }
}
