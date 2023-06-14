package com.nails.nastya.nailsme.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Schedule")
@Builder
public class Schedule {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;

    private Integer masterId;

    @ElementCollection
    private List<Integer> bookedSlots;

    private Instant workFrom;

    private Instant workTo;

    private Integer stepInHour;
}
