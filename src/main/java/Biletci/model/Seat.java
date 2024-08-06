package Biletci.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Seat extends BaseEntity{

    private int seatNumber;

    @ManyToOne
    private TicketHolder ticketHolder;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private OccasionPlace occasionPlace;
}
