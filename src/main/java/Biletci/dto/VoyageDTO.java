package Biletci.dto;

import Biletci.enums.City;
import Biletci.enums.VehicleType;
import Biletci.model.Company;
import Biletci.model.Seat;
import Biletci.model.Vehicle;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class VoyageDTO extends BaseDTO{
    private String name;
    private VehicleDTO vehicle;
    private List<SeatDTO> seats;
    private CompanyDTO company; // Seferi düzenleyen firma
    private City departureCity; // Kalkış şehri
    private City arrivalCity; // Varış şehri
    private LocalDate date; // Tarih
    private String time; // Saat
    private double price; // Fiyat
}
