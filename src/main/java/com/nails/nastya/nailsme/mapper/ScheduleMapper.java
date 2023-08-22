package com.nails.nastya.nailsme.mapper;

import com.nails.nastya.nailsme.dto.ScheduleDto;
import com.nails.nastya.nailsme.persistance.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScheduleMapper {

    ScheduleDto entityToDto(Schedule entity);

    Schedule dtoToEntity(ScheduleDto dto);

    List<ScheduleDto> entityListToDtoList(List<Schedule> entities);

    List<Schedule> dtoListToEntityList(List<ScheduleDto> dtos);
}
