package com.nails.nastya.nailsme.web;

import com.nails.nastya.nailsme.NailsmeApplication;
import com.nails.nastya.nailsme.facade.TimeSlotFacade;
import com.nails.nastya.nailsme.web.response.TimeSlotResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
@RequestMapping(NailsmeApplication.VERSION_URL + "/window")
@RequiredArgsConstructor
@Tag(name = "Окошки", description = "Работа со окнами")
public class TimeSlotController {

    private TimeSlotFacade timeSlotFacade;

    @Autowired
    public TimeSlotController(TimeSlotFacade timeSlotFacade) {
        this.timeSlotFacade = timeSlotFacade;
    }

    @Operation(summary = "Получение всех окошек в за период")
    @GetMapping("/available")
    public TimeSlotResponse getAllWindows(@PathVariable Long masterId,
                                          @PathVariable Instant from,
                                          @PathVariable Instant to) {
        return timeSlotFacade.getAllAvailableTimeslots(masterId, from, to);
    }


    // admin get all timetable
}
