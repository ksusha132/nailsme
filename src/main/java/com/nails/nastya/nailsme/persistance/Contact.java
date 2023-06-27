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
    @Column(name = "insta_link", nullable = false)
    private String instaLink;
    @Column(name = "work_insta_link", nullable = false)
    private String workInstaLink;
    @Column(name = "telegram_nick", nullable = false)
    private String telegramNick;
    @Column(name = "viber_link", nullable = false)
    private String viberLink;
    @Column(name = "watsupp_link", nullable = false)
    private String watsuppLink;
}
