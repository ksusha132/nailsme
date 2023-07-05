package com.nails.nastya.nailsme.web.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private final String login;

    private final String email;

}
