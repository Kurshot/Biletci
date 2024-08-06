package Biletci.mapper;

import Biletci.dto.OccasionPlaceDTO;
import Biletci.model.OccasionPlace;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface OccasionPlaceMapper extends BaseMapper<OccasionPlace, OccasionPlaceDTO>{
}
