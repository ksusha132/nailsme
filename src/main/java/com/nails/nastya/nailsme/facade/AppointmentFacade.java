package com.nails.nastya.nailsme.facade;

import com.nails.nastya.nailsme.service.AppointmentService;
import com.nails.nastya.nailsme.web.request.AppointmentRequest;
import com.nails.nastya.nailsme.web.response.AppointmentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppointmentFacade {

    private final AppointmentService appointmentService;

    public AppointmentFacade(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    public AppointmentResponse createAnAppointment(AppointmentRequest request) {
        // mapper
        appointmentService.createAnAppointment(null);
        return null;
    }
}
