package com.nails.nastya.nailsme.persistance;

import com.nails.nastya.nailsme.enumeration.OrderStep;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * Лог действий пользователя в телеге.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_order_detail")
@Builder
public class UserOrderDetail {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "user_order_detail_id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "user_order_id", nullable = false)
    private Integer userOrderId;

    @Column(name = "client_id", nullable = true)
    private Integer clientId;

    @Column(name = "state", nullable = true)
    @Enumerated(EnumType.STRING)
    private OrderStep state;

    @Column(name = "value_field", nullable = true)
    private String valueField;

    @Column(name = "prior_state_id", nullable = true)
    private Integer priorStateId;

    @Column(name = "create_at", nullable = false)
    private Instant createAt;

}
