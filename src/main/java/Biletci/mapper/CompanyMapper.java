package Biletci.mapper;

import Biletci.dto.CompanyDTO;
import Biletci.model.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper extends BaseMapper<Company, CompanyDTO> {}