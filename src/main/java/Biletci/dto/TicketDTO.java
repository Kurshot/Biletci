package Biletci.dto;

import Biletci.enums.City;
import Biletci.enums.TicketStatus;
import Biletci.enums.TicketType;
import Biletci.model.Occasion;
import Biletci.model.User;
import Biletci.model.Voyage;
import lombok.Data;


@Data
public class TicketDTO extends BaseDTO{
    private String name;
    private int seatNumber;
    private City arrivalCity;
    private City departureCity;
    private String companyName;
    private TicketStatus ticketStatus;
    private TicketType ticketType;
    private Long voId;
    private TicketHolderDTO ticketHolder;
}

