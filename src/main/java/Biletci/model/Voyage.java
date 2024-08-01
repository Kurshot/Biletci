package Biletci.model;

import Biletci.enums.City;
import Biletci.enums.VoyageType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="voyages")
public class Voyage extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private City departureCity;

    @Enumerated(EnumType.STRING)
    private City arrivalCity;

    private LocalDate date;
    private String time;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company voyageCompany;

    @Enumerated(EnumType.STRING)
    private VoyageType voyageType;

    private double price;
}

