package Biletci.dto;

import Biletci.enums.City;
import Biletci.enums.TicketStatus;
import Biletci.enums.TicketType;


import lombok.Data;

@Data
public class TicketWithTicketHolderDTO extends BaseDTO{
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
