package com.nails.nastya.nailsme.dto;

import com.nails.nastya.nailsme.enumeration.AppointmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class AppointmentDto {
    private Integer id;
    private Integer clientId; // whom
    private Integer masterId; // who
    private Integer serviceId; // what
    private Integer windowId; // when
    private AppointmentStatus status;
}
