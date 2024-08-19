package Biletci.mapper;

import Biletci.dto.TicketDTO;
import Biletci.dto.TicketWithTicketHolderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketWithTicketHolderMapper {

    TicketWithTicketHolderDTO toTicketWithTicketHolderDTO(TicketDTO ticketDTO);

    TicketDTO toTicketDTO(TicketWithTicketHolderDTO ticketWithTicketHolderDTO);
}