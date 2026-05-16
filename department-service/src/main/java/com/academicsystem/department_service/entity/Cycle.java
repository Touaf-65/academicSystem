package com.academicsystem.department_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cycles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer dureeAnnees;

    @Column(nullable = false)
    private Long programId;
}