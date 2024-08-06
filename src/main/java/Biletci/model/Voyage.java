package Biletci.model;

import Biletci.enums.City;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name="voyages")
public class Voyage extends BaseEntity{

    private String name;

    @ManyToOne
    private Vehicle vehicle;

    @OneToMany
    private List<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company; // Seferi düzenleyen firma

    @Enumerated(EnumType.STRING)
    private City departureCity; // Kalkış şehri

    @Enumerated(EnumType.STRING)
    private City arrivalCity; // Varış şehri

    private LocalDate date; // Tarih

    private String time; // Saat

    private double price; // Fiyat
}

