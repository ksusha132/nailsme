package com.nails.nastya.nailsme.repository;

import com.nails.nastya.nailsme.persistance.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAllByClientId(Integer clientId);
}

