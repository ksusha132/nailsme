package com.nails.nastya.nailsme.service;

import com.nails.nastya.nailsme.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    AppointmentDto createAnAppointment(AppointmentDto appointmentDto);

    AppointmentDto updateAnAppointment(AppointmentDto appointmentDto);

    void deleteAnAppointment(Integer appointmentId);

    List<AppointmentDto> getAppointmentsByClient(Integer clientId);

    List<AppointmentDto> getAppointmentsByMaster(Integer masterId);
}
