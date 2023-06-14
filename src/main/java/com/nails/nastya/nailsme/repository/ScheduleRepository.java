package com.nails.nastya.nailsme.repository;

import com.nails.nastya.nailsme.persistance.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
