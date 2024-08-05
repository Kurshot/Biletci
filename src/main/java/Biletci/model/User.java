package Biletci.model;

import Biletci.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static Biletci.enums.UserRole.USER;

@Data
@Entity
@Table(name="users")
public class User extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;
    private UserRole role = USER;

}