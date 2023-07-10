package com.nails.nastya.nailsme.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ScheduleDto {
    private Integer id;

    private Integer masterId;

    private List<Integer> bookedSlots;

    private Instant workFrom;

    private Instant workTo;

    private Integer stepInHour;
}
