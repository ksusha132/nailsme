package com.nails.nastya.nailsme.service;

import com.nails.nastya.nailsme.dto.TimeSlotDto;

import java.time.Instant;
import java.util.List;

public interface TimeSlotService {
    List<TimeSlotDto> getAllAvailableTimeSlots(Integer masterId,
                                               Instant from,
                                               Instant to);
}
