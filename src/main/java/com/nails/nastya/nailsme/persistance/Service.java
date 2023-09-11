package com.nails.nastya.nailsme.persistance;

import com.nails.nastya.nailsme.enumeration.SpecialityKind;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "service")
@Builder
public class Service {

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;

    @Column(name = "groupId")
    private Integer groupId;

    @Column(name = "name")
    private String name;

}
