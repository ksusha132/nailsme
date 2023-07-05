package com.nails.nastya.nailsme.web.request;

import lombok.Data;

@Data
public class RegisterClientRequest {
    private final String name;
    private final String login;
    private final Long phone;
    private final String telegramNick;
}
