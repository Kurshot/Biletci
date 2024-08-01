package Biletci.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="companies")
public class Company extends BaseEntity{

    private String companyName;
    private String companyLogo;
    private String companyPhone;



}

