package com.nails.nastya.nailsme.service.impl;

import com.nails.nastya.nailsme.repository.TimeSlotRepository;
import com.nails.nastya.nailsme.service.TimeSlotService;
import com.nails.nastya.nailsme.web.response.TimeSlotResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class TimeSlotServiceImpl implements TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    public TimeSlotResponse getAllAvailableTimeSlots(Long masterId, Instant from, Instant to) {
        return null;
    }

    @Override
    public TimeSlotResponse getAllTimeSlots(Long masterId, Instant from, Instant to) {
        return null;
    }
}
