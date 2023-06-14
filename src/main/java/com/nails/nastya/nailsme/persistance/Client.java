package com.nails.nastya.nailsme.persistance;

import com.nails.nastya.nailsme.enumeration.ClientStatus;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.asm.IProgramElement;

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
    private String name;
    private String telegramNick;
    private String phone; // 79117714269
    private Instant dateBirth;
    @Enumerated(EnumType.STRING)
    private ClientStatus status;
}
