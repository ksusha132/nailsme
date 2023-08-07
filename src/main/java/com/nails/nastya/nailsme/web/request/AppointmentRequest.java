package com.nails.nastya.nailsme.web.request;

import lombok.Data;

@Data
public class AppointmentRequest {
    private Integer clientId; // whom
    private Integer masterId; // who
    private Integer serviceId; // what
    private Integer windowId; // when
}
