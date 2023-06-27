package com.nails.nastya.nailsme.service;

import com.nails.nastya.nailsme.dto.AppointmentDto;

public interface AppointmentService {
    AppointmentDto createAnAppointment(AppointmentDto appointmentDto);

    void editAnAppointment();

    void deleteAnAppointment();
}
