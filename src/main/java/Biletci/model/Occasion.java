package Biletci.model;

import Biletci.enums.City;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;

@Entity
@Data
@Table(name="occasions")
public class Occasion extends BaseEntity{

    private String name;
    private String description;
    private String eventImage;

    @Enumerated(EnumType.STRING)
    private City eventCity;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @Lazy
    private Company occasionCompany;

    private String fullAddress;
    private LocalDate date;
    private String time;
    private double price;
}
