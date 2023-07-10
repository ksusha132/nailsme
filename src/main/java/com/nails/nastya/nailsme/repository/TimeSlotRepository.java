package com.nails.nastya.nailsme.repository;

import com.nails.nastya.nailsme.persistance.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
    List<TimeSlot> getTimeSlotsByWorkFromAfterAndWorkToBeforeAndMasterId(Instant from, Instant to, Integer id);
}
