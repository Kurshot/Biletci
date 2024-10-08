package Biletci.mapper;

import Biletci.dto.LoginDTO;
import Biletci.dto.RegisterDTO;
import Biletci.dto.UserDTO;
import Biletci.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {

    UserDTO registerDTOtoUserDTO(RegisterDTO registerDTO);
    UserDTO loginDTOtoUserDTO(LoginDTO loginDTO);
}
