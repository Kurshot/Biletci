package Biletci.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Seat extends BaseEntity{

    private int seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private TicketHolder ticketHolder;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    private OccasionPlace occasionPlace;
}
