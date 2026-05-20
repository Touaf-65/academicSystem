package com.academicsystem.planning_service.service;

import java.time.DayOfWeek;
import java.time.LocalTime;

public interface ConflictDetectionService {

    void checkRoomConflict(
            Long roomId,
            DayOfWeek day,
            LocalTime start,
            LocalTime end,
            Long excludeId
    );

    void checkTeacherConflict(
            Long teacherId,
            DayOfWeek day,
            LocalTime start,
            LocalTime end,
            Long excludeId
    );

    void checkClassConflict(
            Long classId,
            DayOfWeek day,
            LocalTime start,
            LocalTime end,
            Long excludeId
    );

    void checkGroupConflict(
            Long groupId,
            DayOfWeek day,
            LocalTime start,
            LocalTime end,
            Long excludeId
    );
}