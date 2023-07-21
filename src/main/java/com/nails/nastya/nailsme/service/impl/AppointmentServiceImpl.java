package com.nails.nastya.nailsme.service.impl;

import com.nails.nastya.nailsme.dto.AppointmentDto;
import com.nails.nastya.nailsme.enumeration.AppointmentStatus;
import com.nails.nastya.nailsme.exception.AppointmentException;
import com.nails.nastya.nailsme.mapper.AppointmentMapper;
import com.nails.nastya.nailsme.persistance.Appointment;
import com.nails.nastya.nailsme.repository.AppointmentRepository;
import com.nails.nastya.nailsme.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public AppointmentDto createAnAppointment(AppointmentDto appointmentDto) {
        log.info("AppointmentDto: {}", appointmentDto);
        Appointment appointment = appointmentMapper.appointmentDtoToAppointment(appointmentDto);
        log.info("Mapped appointment: {}", appointment);
        appointment.setStatus(AppointmentStatus.RESERVED);
        Appointment saved = appointmentRepository.saveAndFlush(appointment);
        log.info("Saved appointment: {}", saved);
        AppointmentDto appointmentDtoSaved = appointmentMapper.appointmentToAppointmentDto(appointment);
        return appointmentDtoSaved;
    }

    @Override
    public AppointmentDto updateAnAppointment(AppointmentDto appointmentDto) {
        return null;
    }

    @Override
    public void deleteAnAppointment(Integer appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException("Not found appointment with id: " + appointmentId));
        log.info("Appointment to be deleted: {}", appointment);
        appointmentRepository.delete(appointment);
    }

    @Override
    public List<AppointmentDto> getAppointmentsByClient(Integer clientId) {
        List<Appointment> appointments = appointmentRepository.findAllByClientId(clientId);
        log.info("Found appointments: {}", appointments);
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        appointments.forEach(appointment -> appointmentDtos.add(
                appointmentMapper.appointmentToAppointmentDto(appointment))
        );
        log.info("Mapped appointmentDtos: {}", appointmentDtos);
        return appointmentDtos;
    }
}
