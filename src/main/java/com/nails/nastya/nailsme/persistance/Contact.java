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
    private String instaLink;
    private String workInstaLink;
    private String telegramNick;
    private String viberLink;
    private String watsuppLink;
}
