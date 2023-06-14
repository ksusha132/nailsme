package com.nails.nastya.nailsme.persistance;

import com.nails.nastya.nailsme.enumeration.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Appointment")
@Builder
public class Appointment {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;
    private Integer clientId; // whom
    private Integer masterId; // who
    private Integer serviceId; // what
    private Integer windowId; // when
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
