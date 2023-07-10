package com.nails.nastya.nailsme.dto;

import com.nails.nastya.nailsme.enumeration.TimeSlotState;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
public class TimeSlotDto {
    private Integer id;

    private Instant workFrom;

    private Instant workTo;

    private Integer masterId;

    private TimeSlotState timeSlotState;
}
