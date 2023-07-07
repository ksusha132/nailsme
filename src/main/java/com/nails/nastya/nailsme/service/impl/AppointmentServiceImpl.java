package com.nails.nastya.nailsme.service.impl;

import com.nails.nastya.nailsme.dto.AppointmentDto;
import com.nails.nastya.nailsme.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Override
    public AppointmentDto createAnAppointment(AppointmentDto appointmentDto) {
        return null;
    }

    @Override
    public AppointmentDto updateAnAppointment(AppointmentDto appointmentDto) {
        return null;
    }

    @Override
    public void deleteAnAppointment(Integer appointmentId) {

    }

    @Override
    public List<AppointmentDto> getAppointmentsByLogin(String login) {
        return null;
    }
}
