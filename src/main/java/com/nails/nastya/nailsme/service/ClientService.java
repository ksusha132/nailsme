package com.nails.nastya.nailsme.service;

import com.nails.nastya.nailsme.dto.ClientDto;
import com.nails.nastya.nailsme.dto.ConfirmNewClientDto;
import com.nails.nastya.nailsme.dto.LoginDto;
import com.nails.nastya.nailsme.dto.ResetPasswordDto;

public interface ClientService {
    ClientDto registerNewClient(ClientDto clientDto);

    ConfirmNewClientDto confirmNewClient(String token);

    LoginDto login(String login, String password);

    ResetPasswordDto resetPassword(ResetPasswordDto resetPasswordDto);

    ClientDto getClientById(Integer id);
}
