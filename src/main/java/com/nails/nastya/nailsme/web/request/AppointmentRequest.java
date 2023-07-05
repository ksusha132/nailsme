package com.nails.nastya.nailsme.web.request;

import lombok.Data;

@Data
public class AppointmentRequest {
    private final String clientName;
    private final Long phoneNumber;
    private final String telegrammNick;
    private final Integer masterId;
    private Integer serviceId;
    private Integer windowId;
}
