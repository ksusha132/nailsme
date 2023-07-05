package com.nails.nastya.nailsme.web.response;

import com.nails.nastya.nailsme.dto.TimeSlotDto;
import lombok.Data;

import java.util.List;

@Data
public class TimeSlotResponse {
    private List<TimeSlotDto> windowDtoList;
}
