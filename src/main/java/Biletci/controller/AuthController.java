package Biletci.controller;

import Biletci.dto.AuthResponseDTO;
import Biletci.dto.LoginDTO;
import Biletci.dto.RegisterDTO;
import Biletci.dto.UserDTO;
import Biletci.enums.ResultMapping;
import Biletci.enums.UserRole;
import Biletci.model.User;
import Biletci.repository.UserRepository;
import Biletci.security.JWTGenerator;
import Biletci.service.GenericServiceResult;
import Biletci.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private JWTGenerator jwtGenerator;

    private UserService userService;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository
    , JWTGenerator jwtGenerator, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }


    @PostMapping("login")
    public GenericServiceResult login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail() , loginDTO.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new GenericServiceResult(ResultMapping.SUCCESS, new AuthResponseDTO(token));
    }


    @PostMapping("register")
    public GenericServiceResult register(@Valid @RequestBody RegisterDTO registerDTO) {
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            return new GenericServiceResult(ResultMapping.ERROR_FIELD, "Email is already in use");
        }
        userService.createUser(registerDTO);
        return new GenericServiceResult(ResultMapping.CREATED, "Registered successfully.");
    }
}
