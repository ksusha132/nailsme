package com.nails.nastya.nailsme.facade;

import com.nails.nastya.nailsme.dto.TimeSlotDto;
import com.nails.nastya.nailsme.mapper.TimeSlotMapper;
import com.nails.nastya.nailsme.service.TimeSlotService;
import com.nails.nastya.nailsme.web.response.TimeSlotResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@Slf4j
public class TimeSlotFacade {

    private final TimeSlotService timeSlotService;
    private final TimeSlotMapper timeSlotMapper;

    public TimeSlotFacade(TimeSlotService timeSlotService,
                          TimeSlotMapper timeSlotMapper) {
        this.timeSlotService = timeSlotService;
        this.timeSlotMapper = timeSlotMapper;
    }

    public List<TimeSlotResponse> getAllAvailableTimeslots(Integer masterId,
                                                           Instant from,
                                                           Instant to) {
        List<TimeSlotDto> timeSlotDtos = timeSlotService.getAllAvailableTimeSlots(masterId, from, to);
        log.info("TimeSlotResponse getAllAvailableTimeslots : {}, masterId {}", timeSlotDtos, masterId);
        List<TimeSlotResponse> timeSlotResponseList = timeSlotMapper.timeSlotDtoListToTimeSlotResponseList(timeSlotDtos);
        log.info("Mapped timeSlotResponseList {}, masterId {}", timeSlotResponseList, masterId);
        return timeSlotResponseList;
    }
}
