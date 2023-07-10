package com.nails.nastya.nailsme.dto;

import lombok.Data;

import java.util.List;

@Data
public class MasterDto {
    private Integer id;
    private Integer contactId;
    private String name;
    private List<String> specialities;
}
