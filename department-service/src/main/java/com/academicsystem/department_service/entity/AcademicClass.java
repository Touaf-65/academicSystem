package com.academicsystem.department_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "academic_classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Integer capacite;

    @Column(nullable = false)
    private Long levelId;
}