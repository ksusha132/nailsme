package com.nails.nastya.nailsme.web.response;

import lombok.Data;

import java.util.List;

@Data
public class AppointmentsResponse {
    List<AppointmentResponse> appointmentResponses;
}
