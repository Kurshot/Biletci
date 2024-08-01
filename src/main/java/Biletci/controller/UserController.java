package Biletci.controller;

import Biletci.dto.UserDTO;
import Biletci.enums.ResultMapping;
import Biletci.model.User;
import Biletci.service.GenericServiceResult;
import Biletci.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public GenericServiceResult getAllUsers(){
        List<UserDTO> result = userService.getAllUsers();
        return new GenericServiceResult(ResultMapping.SUCCESS, result);
    }

    @GetMapping("/{id}")
    public GenericServiceResult getUserById(@PathVariable Long id) {
        UserDTO result = userService.getUserById(id);
        if(result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
        else{
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @PostMapping
    public GenericServiceResult createUser(@RequestBody UserDTO userDTO) {
        UserDTO result = userService.createUser(userDTO);
        return new GenericServiceResult(ResultMapping.CREATED, userDTO);
    }

    @PutMapping
    public GenericServiceResult updateUser(@RequestBody UserDTO userDTO) {
        UserDTO result = userService.updateUser(userDTO);
        if (result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, userDTO);
        }
    }

    @DeleteMapping("/{id}")
    public GenericServiceResult deleteUser(@PathVariable Long id) {
        boolean result = userService.deleteUser(id);
        if (result) {
            return new GenericServiceResult(ResultMapping.SUCCESS, null);
        } else {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
    }
}
