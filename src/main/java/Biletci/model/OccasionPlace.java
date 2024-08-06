package Biletci.model;

import Biletci.enums.TicketType;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Data
@Entity

public class OccasionPlace extends BaseEntity{
    private String placeName;
    private String fullAddress;
    private TicketType ticketType;
}
