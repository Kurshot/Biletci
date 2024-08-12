package Biletci.mapper;

import Biletci.dto.TicketDTO;
import Biletci.model.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CompanyMapper.class,
        VoyageMapper.class, OccasionMapper.class, TicketHolderMapper.class})
public interface TicketMapper extends BaseMapper<Ticket, TicketDTO>{
}
