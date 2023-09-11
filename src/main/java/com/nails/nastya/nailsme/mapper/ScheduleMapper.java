package com.nails.nastya.nailsme.mapper;

import com.nails.nastya.nailsme.dto.ScheduleDto;
import com.nails.nastya.nailsme.persistance.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScheduleMapper {

    ScheduleDto scheduleToScheduleDto(Schedule entity);

    Schedule scheduleDtoToSchedule(ScheduleDto dto);

    List<ScheduleDto> scheduleToScheduleDtoList(List<Schedule> entities);

    List<Schedule> scheduleDtoToScheduleList(List<ScheduleDto> dtos);
}
