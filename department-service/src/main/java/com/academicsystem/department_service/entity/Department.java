package com.academicsystem.department_service.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "departments")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @Column(length = 1000)
    private String description;
}