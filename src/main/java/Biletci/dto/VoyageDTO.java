package Biletci.dto;

import Biletci.enums.City;
import Biletci.enums.VehicleType;
import Biletci.model.Company;
import Biletci.model.Seat;
import Biletci.model.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.aspectj.bridge.IMessage;

import java.time.LocalDate;
import java.util.List;

@Data
public class VoyageDTO extends BaseDTO{

    private String name;

    @NotNull(message = "Vehicle cannot be null")
    private VehicleDTO vehicle;

    private List<SeatDTO> seats;

    private int emptySeatCount;

    private CompanyDTO company; // Seferi düzenleyen firma

    private City departureCity; // Kalkış şehri

    private City arrivalCity; // Varış şehri

    private LocalDate date; // Tarih

    private String time; // Saat

    private double price; // Fiyat
}
