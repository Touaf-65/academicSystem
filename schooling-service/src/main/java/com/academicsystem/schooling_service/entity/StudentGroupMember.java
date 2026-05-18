package com.academicsystem.schooling_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_group_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private Long groupId;
}