package com.academicsystem.schooling_service.entity;

import com.academicsystem.schooling_service.enums.GroupType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Enumerated(EnumType.STRING)
    private GroupType type;

    private Integer capacite;


    private Long classRoomId;
}