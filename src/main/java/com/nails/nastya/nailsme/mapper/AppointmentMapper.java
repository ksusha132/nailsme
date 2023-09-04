package com.nails.nastya.nailsme.mapper;

import com.nails.nastya.nailsme.dto.AppointmentDto;
import com.nails.nastya.nailsme.persistance.Appointment;
import com.nails.nastya.nailsme.web.request.AppointmentRequest;
import com.nails.nastya.nailsme.web.response.AppointmentResponse;
import com.nails.nastya.nailsme.web.response.AppointmentsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppointmentMapper {

    List<AppointmentResponse> appointmentDtoToAppointmentResponseList(List<AppointmentDto> appointmentDtos);

    List<AppointmentDto> appointmentsToAppointmentsDtoList(List<Appointment> appointments);

    AppointmentDto appointmentRequestToAppointmentDto(AppointmentRequest appointmentRequest);

    AppointmentResponse appointmentDtoToAppointmentResponse(AppointmentDto appointmentDto);

    Appointment appointmentDtoToAppointment(AppointmentDto appointmentDto);

    AppointmentDto appointmentToAppointmentDto(Appointment appointment);
}
