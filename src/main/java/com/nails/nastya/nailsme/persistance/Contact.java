package com.nails.nastya.nailsme.persistance;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Contact")
@Builder
public class Contact {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;
    @Column(name = "insta_link", nullable = true)
    private String instaLink;
    @Column(name = "work_insta_link", nullable = true)
    private String workInstaLink;
    @Column(name = "telegram_nick", nullable = true)
    private String telegramNick;
    @Column(name = "viber_link", nullable = true)
    private String viberLink;
    @Column(name = "whats_app_link", nullable = true)
    private String whatsAppLink;
}
