package com.nails.nastya.nailsme.service.impl;

import com.nails.nastya.nailsme.dto.ClientDto;
import com.nails.nastya.nailsme.dto.ConfirmNewClientDto;
import com.nails.nastya.nailsme.dto.LoginDto;
import com.nails.nastya.nailsme.dto.ResetPasswordDto;
import com.nails.nastya.nailsme.service.ClientService;
import com.nails.nastya.nailsme.web.request.ResetPasswordRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    @Override
    public ClientDto registerNewClient(ClientDto clientDto) {
        return null;
    }

    @Override
    public ConfirmNewClientDto confirmNewClient(String token) {
        return null;
    }

    @Override
    public LoginDto login(String login, String password) {
        return null;
    }

    @Override
    public ResetPasswordDto resetPassword(ResetPasswordDto resetPasswordDto) {
        return null;
    }

    @Override
    public ClientDto getClientById(Integer id) {
        return null;
    }

    @Override
    public ClientDto getClientByLogin(String login) {
        return null;
    }
}
