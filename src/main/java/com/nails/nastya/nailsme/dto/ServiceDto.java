package com.nails.nastya.nailsme.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceDto {
    private Integer id;

    private String name;

    private Integer masterId;

    private BigDecimal price;
}
