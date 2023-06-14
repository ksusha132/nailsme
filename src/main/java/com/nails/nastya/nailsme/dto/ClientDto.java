package com.nails.nastya.nailsme.dto;

import com.nails.nastya.nailsme.enumeration.ClientStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.Instant;

@Data
public class ClientDto {
    private Integer id;
    private String name;
    private String telegramNick;
    private String phone; // 79117714269
    private Instant dateBirth;
    private ClientStatus status;
}
