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
        return appointmentMapper.appointmentToAppointmentDto(saved);
    }

    @Override
    public AppointmentDto updateAnAppointment(AppointmentDto appointmentDto) {
        Integer appointmentId = appointmentDto.getId();
        log.info("Starting to update appointment id: {}", appointmentId);

        if (!appointmentRepository.existsById(appointmentId)) {
            log.error("Not found by id: {}, clientId {}", appointmentId, appointmentDto.getClientId());
            throw new AppointmentException("Not found appointment with this id: " + appointmentId);
        }

        Appointment mappedAppointment = appointmentMapper.appointmentDtoToAppointment(appointmentDto);
        log.info("Mapped appointment: {}", mappedAppointment);
        Appointment saved = appointmentRepository.saveAndFlush(mappedAppointment);
        log.info("Saved appointment: {}", saved);
        return appointmentMapper.appointmentToAppointmentDto(saved);
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
        List<AppointmentDto> appointmentDtos = appointmentMapper.appointmentsToAppointmentsDtoList(appointments);
        log.info("Mapped appointmentDtos: {}", appointmentDtos);
        return appointmentDtos;
    }

    @Override
    public List<AppointmentDto> getAppointmentsByMaster(Integer masterId) {
        List<Appointment> appointments = appointmentRepository.findAllByMasterId(masterId);
        log.info("Found appointments: {}", appointments);
        List<AppointmentDto> appointmentDtos = appointmentMapper.appointmentsToAppointmentsDtoList(appointments);
        log.info("Mapped appointmentDtos: {}", appointmentDtos);
        return appointmentDtos;
    }
}
