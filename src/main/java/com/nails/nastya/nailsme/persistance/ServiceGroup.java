package com.nails.nastya.nailsme.persistance;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "service_group")
@Builder
public class ServiceGroup {

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

}
