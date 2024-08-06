package Biletci.dto;

import Biletci.enums.TicketStatus;
import Biletci.enums.TicketType;
import Biletci.model.Occasion;
import Biletci.model.User;
import Biletci.model.Voyage;
import lombok.Data;


@Data
public class TicketDTO extends BaseDTO{
    private TicketStatus ticketStatus;
    private TicketType ticketType;
    private VoyageDTO ticketVoyage;
    private OccasionDTO ticketOccasion;
}

