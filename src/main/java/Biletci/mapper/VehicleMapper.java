package Biletci.mapper;

import Biletci.dto.VehicleDTO;
import Biletci.model.Vehicle;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface VehicleMapper extends BaseMapper<Vehicle, VehicleDTO>{
}
