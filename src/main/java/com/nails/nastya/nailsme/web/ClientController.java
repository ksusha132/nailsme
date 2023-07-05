package com.nails.nastya.nailsme.web;

import com.nails.nastya.nailsme.NailsmeApplication;
import com.nails.nastya.nailsme.facade.ClientFacade;
import com.nails.nastya.nailsme.web.request.LoginRequest;
import com.nails.nastya.nailsme.web.request.RegisterClientRequest;
import com.nails.nastya.nailsme.web.request.ResetPasswordRequest;
import com.nails.nastya.nailsme.web.response.MasterServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(NailsmeApplication.VERSION_URL + "/client")
@RequiredArgsConstructor
@Tag(name = "Клиент", description = "Работа с клиантами")
public class ClientController {

    private final ClientFacade clientFacade;


    @Operation(summary = "Регистрация клиента")
    @PostMapping("/register")
    public MasterServiceResponse registerNewClient(@RequestBody RegisterClientRequest registerClientRequest) {
        return null;
    }

    @Operation(summary = "Подтверждение регистрации клиента")
    @PostMapping("/confirm/{token}")
    public MasterServiceResponse confirmNewClient(@PathVariable String token) {
        return null;
    }

    @Operation(summary = "Логин клиента")
    @PostMapping("/login")
    public MasterServiceResponse login(@RequestBody LoginRequest loginRequest) {
        return null;
    }

    @Operation(summary = "Подтверждение регистрации клиента")
    @PostMapping("/reset")
    public MasterServiceResponse resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        return null;
    }
}
