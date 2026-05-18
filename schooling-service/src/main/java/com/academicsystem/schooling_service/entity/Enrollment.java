package com.academicsystem.schooling_service.entity;

import com.academicsystem.schooling_service.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private Long classRoomId;

    @Column(nullable = false)
    private String academicYear;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;
}