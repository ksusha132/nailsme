package com.nails.nastya.nailsme.mapper;

import com.nails.nastya.nailsme.dto.AppointmentDto;
import com.nails.nastya.nailsme.persistance.Appointment;
import com.nails.nastya.nailsme.web.request.AppointmentRequest;
import com.nails.nastya.nailsme.web.response.AppointmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppointmentMapper {

    AppointmentDto entityToDto(Appointment entity);

    Appointment dtoToEntity(AppointmentDto dto);

    List<AppointmentDto> entityListToDtoList(List<Appointment> entities);

    List<Appointment> dtoListToEntityList(List<AppointmentDto> dtos);

    AppointmentDto requestToAppointmentDto(AppointmentRequest request);

    AppointmentResponse appointmentDtoToResponse(AppointmentDto dto);
}
