package com.nails.nastya.nailsme.dto;

import com.nails.nastya.nailsme.enumeration.TimeSlotState;
import lombok.Data;

import java.time.Instant;

@Data
public class TimeSlotDto {
    private Integer id;

    private Instant from;

    private Instant to;

    private TimeSlotState timeSlotState;
}
