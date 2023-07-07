package com.nails.nastya.nailsme.web.response;

import com.nails.nastya.nailsme.dto.AppointmentDto;
import lombok.Data;

import java.util.List;

@Data
public class AppointmentResponse {
    List<AppointmentDto> appointmentDto;
}
