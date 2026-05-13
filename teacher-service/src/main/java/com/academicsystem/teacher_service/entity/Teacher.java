package com.academicsystem.teacher_service.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private Long authUserId;


    @ElementCollection
    @CollectionTable(
            name = "teacher_departments",
            joinColumns = @JoinColumn(name = "teacher_id")
    )
    @Column(name = "department_id")
    private List<Long> departmentIds =
            new ArrayList<>();


    @ElementCollection
    @CollectionTable(
            name = "teacher_courses",
            joinColumns = @JoinColumn(name = "teacher_id")
    )
    @Column(name = "course_id")
    private List<Long> courseIds =
            new ArrayList<>();
}