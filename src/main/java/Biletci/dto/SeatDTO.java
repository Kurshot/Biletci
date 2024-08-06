package Biletci.dto;

import lombok.Data;

@Data
public class SeatDTO extends BaseDTO{
    private int seatNumber;
    private TicketHolderDTO ticketHolder;
    private VehicleDTO vehicle;
    private OccasionPlaceDTO occasionPlace;
}
