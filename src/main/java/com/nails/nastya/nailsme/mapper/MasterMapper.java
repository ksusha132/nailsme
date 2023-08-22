package com.nails.nastya.nailsme.mapper;

import com.nails.nastya.nailsme.dto.MasterDto;
import com.nails.nastya.nailsme.persistance.Master;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MasterMapper {

    MasterDto entityToDto(Master entity);

    Master dtoToEntity(MasterDto dto);

    List<MasterDto> entityListToDtoList(List<Master> entities);

    List<Master> dtoListToEntityList(List<MasterDto> dtos);
}
