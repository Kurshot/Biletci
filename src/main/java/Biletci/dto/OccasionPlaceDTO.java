package Biletci.dto;

import Biletci.enums.TicketType;
import lombok.Data;

@Data
public class OccasionPlaceDTO extends BaseDTO{
    private String placeName;
    private String fullAddress;
    private TicketType ticketType;
}
