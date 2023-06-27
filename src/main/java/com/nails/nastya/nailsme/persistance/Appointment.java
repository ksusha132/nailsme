package com.nails.nastya.nailsme.persistance;

import com.nails.nastya.nailsme.enumeration.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

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
    @Column(name = "client_id", nullable = false)
    private Integer clientId; // whom
    @Column(name = "master_id", nullable = false)
    private Integer masterId; // who
    @Column(name = "service_id", nullable = false)
    private Integer serviceId; // what
    @Column(name = "window_id", nullable = false)
    private Integer windowId; // when
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column(name = "created")
    private Instant created;

    @PrePersist
    protected void onCreate() {
        this.created = Instant.now();
    }
}
