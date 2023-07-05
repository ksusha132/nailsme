package com.nails.nastya.nailsme.web.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class MasterServiceResponse {
    private final Map<String, BigDecimal> services;
}
