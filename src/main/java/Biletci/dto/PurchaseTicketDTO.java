package Biletci.dto;

import Biletci.enums.TicketType;
import lombok.Data;

@Data
public class PurchaseTicketDTO extends BaseDTO{
    private int seatNumber;
    private TicketType ticketType;
    private Long voId;
    private TicketHolderDTO ticketHolder;
}
