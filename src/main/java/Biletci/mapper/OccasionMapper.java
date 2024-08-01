package Biletci.mapper;

import Biletci.dto.OccasionDTO;
import Biletci.model.Occasion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface OccasionMapper extends BaseMapper<Occasion, OccasionDTO>{
}
