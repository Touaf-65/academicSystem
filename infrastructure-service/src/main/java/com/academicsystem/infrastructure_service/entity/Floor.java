package com.academicsystem.infrastructure_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "floors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;


    private Integer capacite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(
            mappedBy = "floor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Room> rooms = new ArrayList<>();
}