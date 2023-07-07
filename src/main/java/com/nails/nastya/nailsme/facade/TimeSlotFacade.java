package com.nails.nastya.nailsme.facade;

import com.nails.nastya.nailsme.service.TimeSlotService;
import com.nails.nastya.nailsme.web.response.AdminTimeSlotResponse;
import com.nails.nastya.nailsme.web.response.TimeSlotResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
public class TimeSlotFacade {

    private final TimeSlotService timeSlotService;

    public TimeSlotFacade(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    public TimeSlotResponse getAllAvailableTimeslots(Long masterId,
                                                     Instant from,
                                                     Instant to) {
        timeSlotService.getAllAvailableTimeSlots(masterId, from, to);

        return null;
    }

    public AdminTimeSlotResponse getAllTimeSlots(Long masterId,
                                                 Instant from,
                                                 Instant to) {
        timeSlotService.getAllTimeSlots(masterId, from, to);
        return null;
    }
}
