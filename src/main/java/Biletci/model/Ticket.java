package Biletci.model;

import Biletci.enums.City;
import Biletci.enums.TicketStatus;
import Biletci.enums.TicketType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ticket extends BaseEntity {

    private String name;
    private int seatNumber;
    private City arrivalCity;
    private City departureCity;
    private String companyName;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus; // PURCHASED - CANCELED - USED

    @Enumerated(EnumType.STRING)
    private TicketType ticketType; // VOYAGE - OCCASION

    private Long voId; // Voyage or Occasion ID

    @ManyToOne
    @JoinColumn(name = "ticket_holder_id") // Optional: specify the column name in the database
    private TicketHolder ticketHolder;
}