package com.nails.nastya.nailsme.service.impl;

import com.nails.nastya.nailsme.dto.TimeSlotDto;
import com.nails.nastya.nailsme.mapper.TimeSlotMapper;
import com.nails.nastya.nailsme.persistance.TimeSlot;
import com.nails.nastya.nailsme.repository.TimeSlotRepository;
import com.nails.nastya.nailsme.service.TimeSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class TimeSlotServiceImpl implements TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotMapper timeSlotMapper;

    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository,
                               TimeSlotMapper timeSlotMapper) {
        this.timeSlotRepository = timeSlotRepository;
        this.timeSlotMapper = timeSlotMapper;
    }

    @Override
    public List<TimeSlotDto> getAllAvailableTimeSlots(Integer masterId, Instant from, Instant to) {
        List<TimeSlot> timeSlots = timeSlotRepository
                .getTimeSlotsByWorkFromAfterAndWorkToBeforeAndMasterId(from, to, masterId);
        log.info("Found time slots in db: {}, MasterId {}, from {}, to {}", timeSlots, masterId, from, to);
        List<TimeSlotDto> timeSlotDtoList = timeSlotMapper.timeSlotListToTimeSlotDtoList(timeSlots);
        log.info("Mapped timeslots: {}, masterId {}", timeSlotDtoList, masterId);
        return timeSlotDtoList;
    }
}
