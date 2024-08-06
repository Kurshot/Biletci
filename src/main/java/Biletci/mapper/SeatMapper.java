package Biletci.mapper;

import Biletci.dto.SeatDTO;
import Biletci.model.OccasionPlace;
import Biletci.model.Seat;
import Biletci.model.TicketHolder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TicketHolder.class, OccasionPlace.class})
public interface SeatMapper extends BaseMapper<Seat, SeatDTO>{
}
