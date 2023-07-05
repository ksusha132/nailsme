package com.nails.nastya.nailsme.web;

import com.nails.nastya.nailsme.NailsmeApplication;
import com.nails.nastya.nailsme.facade.AppointmentFacade;
import com.nails.nastya.nailsme.web.request.AppointmentRequest;
import com.nails.nastya.nailsme.web.response.AppointmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(NailsmeApplication.VERSION_URL + "/appointment")
@RequiredArgsConstructor
@Tag(name = "Встречи", description = "Работа со встречами")
public class AppointmentController {

    private final AppointmentFacade appointmentFacade;


    @Operation(summary = "Создание встречи")
    @PostMapping("/")
    public AppointmentResponse createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return appointmentFacade.createAnAppointment(appointmentRequest);
    }


    @Operation(summary = "Обновление встречи")
    @PutMapping("/")
    public AppointmentResponse updateAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return null;
    }

    @Operation(summary = "Удаление встречи")
    @DeleteMapping("/{appointmentId}")
    public AppointmentResponse deleteAppointment(@PathVariable Integer appointmentId) {
        return null;
    }

    @Operation(summary = "Получение встреч")
    @GetMapping("/{login}")
    public AppointmentResponse getAppointment(@PathVariable String login) {
        return null;
    }
}
