package com.nails.nastya.nailsme.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "price_menu")
@Builder
public class PriceMenu {

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;

    @Column(name = "service_id", nullable = false)
    private Integer serviceId;

    @Column(name = "master_id", nullable = false)
    private Integer masterId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
