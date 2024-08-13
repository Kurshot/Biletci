package Biletci.controller;

import Biletci.dto.AuthResponseDTO;
import Biletci.dto.LoginDTO;
import Biletci.enums.ResultMapping;
import Biletci.mapper.UserMapper;
import Biletci.repository.UserRepository;
import Biletci.security.JWTGenerator;
import Biletci.service.AdminService;
import Biletci.service.GenericServiceResult;
import Biletci.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private AdminService adminService;


    @PostMapping("/auth/login")
    public GenericServiceResult login(@RequestBody LoginDTO loginDTO){
        adminService.login(loginDTO);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail() , loginDTO.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new GenericServiceResult(ResultMapping.SUCCESS, new AuthResponseDTO(token));
    }
}
