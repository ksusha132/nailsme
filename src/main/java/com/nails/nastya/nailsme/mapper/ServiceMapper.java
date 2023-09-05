package com.nails.nastya.nailsme.mapper;

import com.nails.nastya.nailsme.dto.ServiceDto;
import com.nails.nastya.nailsme.persistance.PriceMenu;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServiceMapper {

    ServiceDto serviceToServiceDto(PriceMenu entity);

    PriceMenu serviceDtoToService(ServiceDto dto);

    List<ServiceDto> serviceToServiceDtoList(List<PriceMenu> entities);

    List<PriceMenu> serviceDtoToServiceList(List<ServiceDto> dtos);
}
