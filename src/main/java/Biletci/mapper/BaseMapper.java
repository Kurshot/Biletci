package Biletci.mapper;

import Biletci.model.BaseEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.List;

public interface BaseMapper<Entity extends BaseEntity, DTO> {

    @Named("toEntity")
    Entity toEntity(DTO dto);

    @Named("toDTO")
    DTO toDTO(Entity entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Entity> toEntityList(List<DTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<DTO> toDTOList(List<Entity> entityList);
}

