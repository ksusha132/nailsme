package com.nails.nastya.nailsme.persistance;

import com.nails.nastya.nailsme.enumeration.ClientStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Client")
@Builder
public class Client {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "telegram_nick", nullable = false)
    private String telegramNick;
    @Column(name = "chat_id", nullable = true)
    private Integer chatId;
    @Column(name = "phone", nullable = false)
    private String phone; // 79117714269
    @Column(name = "date_birth", nullable = false)
    private Instant dateBirth;
    @Column(name = "status_id", nullable = false)
    private Integer statusId;
}
