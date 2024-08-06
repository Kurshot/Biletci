package Biletci.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TicketHolderDTO extends BaseDTO{
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
    private String mailAddress;
    private List<TicketDTO> tickets;
}
