package com.nails.nastya.nailsme.service;

import com.nails.nastya.nailsme.web.response.TimeSlotResponse;

import java.time.Instant;

public interface TimeSlotService {
    TimeSlotResponse getAllAvailableTimeSlots(Long masterId,
                                              Instant from,
                                              Instant to);

    TimeSlotResponse getAllTimeSlots(Long masterId,
                                     Instant from,
                                     Instant to);
}
