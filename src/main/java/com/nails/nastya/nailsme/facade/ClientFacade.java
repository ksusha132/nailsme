package com.nails.nastya.nailsme.facade;

import com.nails.nastya.nailsme.service.ClientService;
import com.nails.nastya.nailsme.web.request.LoginRequest;
import com.nails.nastya.nailsme.web.request.RegisterClientRequest;
import com.nails.nastya.nailsme.web.request.ResetPasswordRequest;
import com.nails.nastya.nailsme.web.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ClientFacade {
    private final ClientService clientService;

    public ClientFacade(ClientService clientService) {
        this.clientService = clientService;
    }

    public RegisterNewClientResponse registerNewClient(RegisterClientRequest registerClientRequest) {
        clientService.registerNewClient(null);
        return null;
    }

    public ConfirmNewClientResponse confirmNewClient(String token) {
        clientService.confirmNewClient(token);
        return null;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        clientService.login(loginRequest.getLogin(), loginRequest.getPassword());
        return null;
    }


    public ResetPasswordResponse resetPassword(ResetPasswordRequest resetPasswordRequest) {
        clientService.resetPassword(null);
        return null;
    }
}
