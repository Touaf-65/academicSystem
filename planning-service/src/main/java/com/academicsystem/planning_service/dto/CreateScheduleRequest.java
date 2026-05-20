package com.academicsystem.planning_service.dto;

import com.academicsystem.planning_service.enums.SessionType;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record CreateScheduleRequest(

        @NotNull
        Long classId,

        Long groupId,

        @NotNull
        Long courseId,

        @NotNull
        Long teacherId,

        @NotNull
        Long roomId,

        @NotNull
        DayOfWeek dayOfWeek,

        @NotNull
        LocalTime startTime,

        @NotNull
        LocalTime endTime,

        @NotNull
        Integer semester,

        @NotNull
        String academicYear,

        @NotNull
        SessionType sessionType
) {
}