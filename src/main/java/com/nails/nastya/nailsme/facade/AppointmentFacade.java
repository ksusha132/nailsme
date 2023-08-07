package com.nails.nastya.nailsme.facade;

import com.nails.nastya.nailsme.dto.AppointmentDto;
import com.nails.nastya.nailsme.dto.ClientDto;
import com.nails.nastya.nailsme.mapper.AppointmentMapper;
import com.nails.nastya.nailsme.service.AppointmentService;
import com.nails.nastya.nailsme.service.ClientService;
import com.nails.nastya.nailsme.web.request.AppointmentRequest;
import com.nails.nastya.nailsme.web.response.AppointmentResponse;
import com.nails.nastya.nailsme.web.response.AppointmentsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AppointmentFacade {

    private final AppointmentService appointmentService;

    private final ClientService clientService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentFacade(AppointmentService appointmentService,
                             AppointmentMapper appointmentMapper,
                             ClientService clientService) {
        this.appointmentService = appointmentService;
        this.clientService = clientService;
        this.appointmentMapper = appointmentMapper;
    }


    public AppointmentResponse createAnAppointment(AppointmentRequest request) {
        log.info("AppointmentRequest: {}", request);
        AppointmentDto mappedAppointmentDto = appointmentMapper.appointmentRequestToAppointmentDto(request);
        AppointmentDto savedAppointment = appointmentService.createAnAppointment(mappedAppointmentDto);
        log.info("Saved appointment: {}", savedAppointment);
        AppointmentResponse response = appointmentMapper.appointmentDtoToAppointmentResponse(savedAppointment);
        log.info("Mapped appointment saved: {}", response);
        return response;
    }

    public AppointmentResponse updateAnAppointment(AppointmentRequest request) {
        // mapper
        appointmentService.updateAnAppointment(null);
        return null;
    }

    public void deleteAnAppointment(Integer appointmentId) {
        log.info("Deleting appointment: {}", appointmentId);
        appointmentService.deleteAnAppointment(appointmentId);
    }

    public AppointmentsResponse getAnAppointmentsByLogin(String login) {
        ClientDto clientDto = clientService.getClientByLogin(login);
        log.info("Searching appointments by found client id: {}", clientDto.getId());

        List<AppointmentDto> appointmentDtos = appointmentService.getAppointmentsByClient(clientDto.getId());
        log.info("Found appointments {} by login {}", appointmentDtos, login);

        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        appointmentDtos.forEach(appointmentDto -> appointmentResponses.add(
                appointmentMapper.appointmentDtoToAppointmentResponse(appointmentDto))
        );
        AppointmentsResponse appointmentsResponse = new AppointmentsResponse();
        appointmentsResponse.setAppointmentResponses(appointmentResponses);

        log.info("AppointmentsResponse : {}", appointmentsResponse);
        return appointmentsResponse;
    }

    public AppointmentsResponse getAllAppointmentsByMasterId(Integer masterId) {
        List<AppointmentDto> appointmentDtos = appointmentService.getAppointmentsByMaster(masterId);
        log.info("Found  all appointments {} by masterId {}", appointmentDtos, masterId);

        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        appointmentDtos.forEach(appointmentDto -> appointmentResponses.add(
                appointmentMapper.appointmentDtoToAppointmentResponse(appointmentDto))
        ); //todo mapper list
        AppointmentsResponse appointmentsResponse = new AppointmentsResponse();
        appointmentsResponse.setAppointmentResponses(appointmentResponses);

        log.info("AppointmentsResponse : {}", appointmentsResponse);
        return appointmentsResponse;
    }
}
