package com.nails.nastya.nailsme.mapper;

import com.nails.nastya.nailsme.dto.ServiceDto;
import com.nails.nastya.nailsme.persistance.Service;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServiceMapper {
    ServiceDto entityToDto(Service entity);

    Service dtoToEntity(ServiceDto dto);

    List<ServiceDto> entityListToDtoList(List<Service> entities);

    List<Service> dtoListToEntityList(List<ServiceDto> dtos);
}
