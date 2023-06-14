package com.nails.nastya.nailsme.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class WindowDto {
    private Integer id;

    private Instant from;

    private Instant to;
}
