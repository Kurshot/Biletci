package Biletci.mapper;

import Biletci.dto.VoyageDTO;
import Biletci.model.Voyage;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface VoyageMapper extends BaseMapper<Voyage, VoyageDTO> {



}