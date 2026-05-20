package com.academicsystem.planning_service.dto;

import com.academicsystem.planning_service.enums.SessionType;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record ScheduleResponse(

        Long id,

        Long classId,

        Long groupId,

        Long courseId,

        Long teacherId,

        Long roomId,

        DayOfWeek dayOfWeek,

        LocalTime startTime,

        LocalTime endTime,

        Integer semester,

        String academicYear,

        SessionType sessionType,

        Boolean cancelled
) {
}