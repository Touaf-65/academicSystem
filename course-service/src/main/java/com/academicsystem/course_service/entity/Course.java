package com.academicsystem.course_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Integer volumeHoraire;

    @Column(nullable = false)
    private Long departmentId;

    @ElementCollection
    @CollectionTable(
            name = "course_teachers",
            joinColumns = @JoinColumn(name = "course_id")
    )
    @Column(name = "teacher_id")
    @Builder.Default
    private List<Long> teacherIds = new ArrayList<>();
}