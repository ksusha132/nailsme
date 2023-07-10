package com.nails.nastya.nailsme.mapper;


import com.nails.nastya.nailsme.dto.TimeSlotDto;
import com.nails.nastya.nailsme.persistance.TimeSlot;
import com.nails.nastya.nailsme.web.response.TimeSlotResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TimeSlotMapper {
    List<TimeSlotDto> timeSlotListToTimeSlotDtoList(List<TimeSlot> timeSlots);

    TimeSlotDto timeSlotToTimeSlotDto(TimeSlot timeSlot);

    List<TimeSlotResponse> timeSlotDtoListToTimeSlotResponseList(List<TimeSlotDto> timeSlotDtos);

    TimeSlotResponse timeSlotDtoToTimeSlotResponse(TimeSlotDto timeSlotDto);
}
