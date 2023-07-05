package com.nails.nastya.nailsme.web.request;

import lombok.Data;

@Data
public class LoginRequest {
    private final String login;
    private final String password;
}
