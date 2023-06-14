package com.nails.nastya.nailsme.persistance;

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

    private String name; // enum

    private Integer masterId;

    private BigDecimal price;
}
