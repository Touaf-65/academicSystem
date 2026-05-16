package com.academicsystem.department_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "academic_levels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer ordre;

    @Column(nullable = false)
    private Long cycleId;
}