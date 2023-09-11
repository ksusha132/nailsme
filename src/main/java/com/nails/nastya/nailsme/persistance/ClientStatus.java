package com.nails.nastya.nailsme.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "client_status")
@Builder
public class ClientStatus {

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;

    /**
     * Название статуса
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Процент скидки.
     */
    @Column(name = "percent", nullable = false)
    private BigDecimal percent;
}
