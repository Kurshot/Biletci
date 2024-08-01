package Biletci.dto;

import Biletci.enums.City;
import Biletci.model.Company;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OccasionDTO extends BaseDTO{
    private String name;
    private String description;
    private String occasionImage;
    private City occasionCity;
    private CompanyDTO occasionCompany;
    private String occasionFullAddress;
    private LocalDate date;
    private String time;
    private double price;
}
