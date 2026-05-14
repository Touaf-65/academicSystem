package com.academicsystem.infrastructure_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buildings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String description;


    private Integer capaciteTotale;

    @OneToMany(
            mappedBy = "building",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Floor> floors = new ArrayList<>();
}