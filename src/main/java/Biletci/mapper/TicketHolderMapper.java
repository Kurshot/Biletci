package Biletci.mapper;

import Biletci.dto.TicketHolderDTO;
import Biletci.model.Ticket;
import Biletci.model.TicketHolder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface TicketHolderMapper extends BaseMapper<TicketHolder, TicketHolderDTO>{
}
