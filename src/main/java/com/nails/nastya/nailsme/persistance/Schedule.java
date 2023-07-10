package com.nails.nastya.nailsme.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
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

    @Column(name = "master_Id", nullable = false)
    private Integer masterId;

    @ElementCollection
    private List<Integer> bookedSlots;

    @Column(name = "work_from", nullable = false)
    private Instant workFrom;

    @Column(name = "work_to", nullable = false)
    private Instant workTo;

    @Column(name = "step_in_hour", nullable = false)
    private Integer stepInHour;
}
