package com.nails.nastya.nailsme.persistance;

import com.nails.nastya.nailsme.enumeration.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

/**
 * Лог заказов пользователя в телеге.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_order")
@Builder
public class UserOrder {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "user_order_id", nullable = false)
    private Integer id;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "client_id", nullable = true)
    private Integer clientId;

    @Column(name = "order_name", nullable = true)
    @Enumerated(EnumType.STRING)
    private Order order;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH},
            orphanRemoval = true)
    @JoinColumn(name = "user_order_id")
    private List<UserOrderDetail> details;

    @Column(name = "create_at", nullable = false)
    private Instant createAt;

    @Column(name = "update_at", nullable = false)
    private Instant updateAt;

    @Column(name = "completed", nullable = false)
    private boolean completed = false;

}
