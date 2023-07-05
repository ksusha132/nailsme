package com.nails.nastya.nailsme.web.response;

import com.nails.nastya.nailsme.enumeration.TimeSlotState;
import lombok.Data;

import java.time.Instant;

@Data
public class AdminTimeSlotResponse {
    private Integer id;

    private String service;

    private String clientFio;

    private Instant from;

    private Instant to;

    private TimeSlotState timeSlotState;
}
