package com.academicsystem.planning_service.entity;

import com.academicsystem.planning_service.enums.SessionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "schedule_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // academic structure
    private Long classId;

    private Long groupId;

    // teaching
    private Long courseId;

    private Long teacherId;

    // infrastructure
    private Long roomId;

    // planning
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer semester;

    private String academicYear;

    @Enumerated(EnumType.STRING)
    private SessionType sessionType;

    private Boolean cancelled;
}