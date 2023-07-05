package com.nails.nastya.nailsme.web.response;

import com.nails.nastya.nailsme.enumeration.AppointmentStatus;
import lombok.Data;

@Data
public class AppointmentResponse {
    private final Integer id;
    private final AppointmentStatus status;
}
