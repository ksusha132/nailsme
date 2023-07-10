package com.nails.nastya.nailsme.persistance;

import com.nails.nastya.nailsme.enumeration.TimeSlotState;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "time_slot")
@Builder
public class TimeSlot implements Serializable {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;

    @Column(name = "master_id", nullable = false)
    private Integer masterId;

    @Column(name = "work_from", nullable = false)
    private Instant workFrom;

    @Column(name = "work_to", nullable = false)
    private Instant workTo;
    @Enumerated(EnumType.STRING)
    private TimeSlotState timeSlotState;
}
