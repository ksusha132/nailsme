package com.nails.nastya.nailsme.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Master")
@Builder
public class Master {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;
    @Column(name = "contact_Id", nullable = false)
    private Integer contactId;
    @Column(name = "name", nullable = false)
    private String name;
    @ElementCollection
    private List<String> specialities;
}
