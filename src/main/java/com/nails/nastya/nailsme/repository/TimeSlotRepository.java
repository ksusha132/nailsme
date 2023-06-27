package com.nails.nastya.nailsme.repository;

import com.nails.nastya.nailsme.persistance.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
}
