package com.nails.nastya.nailsme.web;

import com.nails.nastya.nailsme.NailsmeApplication;
import com.nails.nastya.nailsme.facade.AppointmentFacade;
import com.nails.nastya.nailsme.web.request.AppointmentRequest;
import com.nails.nastya.nailsme.web.response.AppointmentResponse;
import com.nails.nastya.nailsme.web.response.AppointmentsResponse;
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
        return appointmentFacade.updateAnAppointment(appointmentRequest);
    }

    @Operation(summary = "Удаление встречи")
    @DeleteMapping("/{appointmentId}")
    public void deleteAppointment(@PathVariable Integer appointmentId) {
        appointmentFacade.deleteAnAppointment(appointmentId);
    }

    @Operation(summary = "Получение встреч")
    @GetMapping("/{login}")
    public AppointmentsResponse getAppointment(@PathVariable String login) {
        return appointmentFacade.getAnAppointmentsByLogin(login);
    }

    // get all appointments for admin
}
