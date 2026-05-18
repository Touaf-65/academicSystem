package com.academicsystem.schooling_service.entity;

import com.academicsystem.schooling_service.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long authUserId;

    @Column(nullable = false)
    private String matricule;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDate dateNaissance;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    private Long departmentId;

    private Long programId;

    private Long cycleId;

    private Long academicLevelId;

    private Long academicClassId;
}