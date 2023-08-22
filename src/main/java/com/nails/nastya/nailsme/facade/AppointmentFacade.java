package com.nails.nastya.nailsme.facade;

import com.nails.nastya.nailsme.dto.AppointmentDto;
import com.nails.nastya.nailsme.mapper.AppointmentMapper;
import com.nails.nastya.nailsme.service.AppointmentService;
import com.nails.nastya.nailsme.web.request.AppointmentRequest;
import com.nails.nastya.nailsme.web.response.AppointmentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppointmentFacade {

    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentResponse createAnAppointment(AppointmentRequest request) {
        AppointmentDto dto = appointmentMapper.requestToAppointmentDto(request);
        return appointmentMapper.appointmentDtoToResponse(appointmentService.createAnAppointment(dto));
    }

    public AppointmentResponse updateAnAppointment(AppointmentRequest request) {
        AppointmentDto dto = appointmentMapper.requestToAppointmentDto(request);
        return appointmentMapper.appointmentDtoToResponse(appointmentService.updateAnAppointment(dto));
    }

    public void deleteAnAppointment(Integer appointmentId) {
        appointmentService.deleteAnAppointment(appointmentId);
    }

    public AppointmentResponse getAnAppointmentsByLogin(String login) {
        appointmentService.getAppointmentsByLogin(login);
        return null;
    }
}
