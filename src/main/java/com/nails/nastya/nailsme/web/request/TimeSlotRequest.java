package com.nails.nastya.nailsme.web.request;

import lombok.Data;

import java.time.Instant;

@Data
public class TimeSlotRequest {
    private final Integer masterId;
    private final Instant from;
    private final Instant to;
}
