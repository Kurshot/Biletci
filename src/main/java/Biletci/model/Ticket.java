package Biletci.model;

import Biletci.enums.TicketStatus;
import Biletci.enums.TicketType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ticket extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User ticketHolder;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @ManyToOne
    @JoinColumn(name = "voyage_id", nullable = true)
    private Voyage ticketVoyage;

    @ManyToOne
    @JoinColumn(name = "occasion_id", nullable = true)
    private Occasion ticketOccasion;
}
