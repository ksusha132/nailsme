package com.nails.nastya.nailsme.persistance;

import com.nails.nastya.nailsme.enumeration.SpecialityKind;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Service")
@Builder
public class Service {

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private SpecialityKind serviceName;
    @Column(name = "master_id", nullable = false)
    private Integer masterId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
