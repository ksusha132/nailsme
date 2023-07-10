package com.nails.nastya.nailsme.web.response;

import com.nails.nastya.nailsme.enumeration.TimeSlotState;
import lombok.Data;

import java.time.Instant;

@Data
public class TimeSlotResponse {
    private Integer id;

    private Instant workFrom;

    private Instant workTo;

    private Integer masterId;

    private TimeSlotState timeSlotState;
}
