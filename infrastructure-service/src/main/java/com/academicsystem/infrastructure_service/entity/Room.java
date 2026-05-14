package com.academicsystem.infrastructure_service.entity;

import com.academicsystem.infrastructure_service.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private Integer capacite;

    private RoomType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    private Floor floor;
}