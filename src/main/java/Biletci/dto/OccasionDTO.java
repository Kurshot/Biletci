package Biletci.dto;

import Biletci.enums.City;
import Biletci.model.Company;
import Biletci.model.OccasionPlace;
import Biletci.model.Seat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;
import java.util.List;

@Data
public class OccasionDTO extends BaseDTO{
    private String name;
    private String description;
    private String eventImage;
    private City eventCity;
    private List<SeatDTO> seats;
    private OccasionPlaceDTO occasionPlace;
    private CompanyDTO company;
    private LocalDate date;
    private String time;
    private double price;
}
