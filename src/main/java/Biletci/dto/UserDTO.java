package Biletci.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO extends BaseDTO{
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private Boolean isAdmin;
}
