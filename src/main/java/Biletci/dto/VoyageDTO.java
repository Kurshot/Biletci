package Biletci.dto;

import Biletci.enums.City;
import Biletci.enums.VoyageType;
import Biletci.model.Company;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VoyageDTO extends BaseDTO{
    private City departureCity;
    private City arrivalCity;
    private LocalDate date;
    private String time;
    private CompanyDTO voyageCompany; // voyageCompany -> id
    private VoyageType voyageType; // VoyageType enum (Seaway , airway, railway, bus)
    private double price;
}
