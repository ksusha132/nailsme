package com.nails.nastya.nailsme.web.response;

import com.nails.nastya.nailsme.enumeration.AppointmentStatus;
import lombok.Data;

@Data
public class AppointmentResponse {
    private Integer id;
    private Integer clientId; // whom
    private Integer masterId; // who
    private Integer serviceId; // what
    private Integer windowId; // when
    private AppointmentStatus status;
}
