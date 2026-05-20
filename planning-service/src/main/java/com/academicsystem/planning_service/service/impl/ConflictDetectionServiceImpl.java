package com.academicsystem.planning_service.service.impl;

import com.academicsystem.planning_service.entity.ScheduleSession;
import com.academicsystem.planning_service.exception.ConflictException;
import com.academicsystem.planning_service.repository.ScheduleRepository;
import com.academicsystem.planning_service.service.ConflictDetectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConflictDetectionServiceImpl
        implements ConflictDetectionService {

    private final ScheduleRepository repository;

    @Override
    public void checkRoomConflict(
            Long roomId,
            DayOfWeek day,
            LocalTime start,
            LocalTime end,
            Long excludeId
    ) {

        List<ScheduleSession> sessions =
                repository.findByRoomIdAndDayOfWeek(
                        roomId,
                        day
                );

        validateConflicts(
                sessions,
                start,
                end,
                excludeId,
                "Room already occupied"
        );
    }

    @Override
    public void checkTeacherConflict(
            Long teacherId,
            DayOfWeek day,
            LocalTime start,
            LocalTime end,
            Long excludeId
    ) {

        List<ScheduleSession> sessions =
                repository.findByTeacherIdAndDayOfWeek(
                        teacherId,
                        day
                );

        validateConflicts(
                sessions,
                start,
                end,
                excludeId,
                "Teacher unavailable"
        );
    }

    @Override
    public void checkClassConflict(
            Long classId,
            DayOfWeek day,
            LocalTime start,
            LocalTime end,
            Long excludeId
    ) {

        List<ScheduleSession> sessions =
                repository.findByClassIdAndDayOfWeek(
                        classId,
                        day
                );

        validateConflicts(
                sessions,
                start,
                end,
                excludeId,
                "Class already has a session"
        );
    }

    @Override
    public void checkGroupConflict(
            Long groupId,
            DayOfWeek day,
            LocalTime start,
            LocalTime end,
            Long excludeId
    ) {

        if (groupId == null) {
            return;
        }

        List<ScheduleSession> sessions =
                repository.findByGroupIdAndDayOfWeek(
                        groupId,
                        day
                );

        validateConflicts(
                sessions,
                start,
                end,
                excludeId,
                "Group already occupied"
        );
    }

    // =====================================================
    // COMMON VALIDATION
    // =====================================================

    private void validateConflicts(
            List<ScheduleSession> sessions,
            LocalTime start,
            LocalTime end,
            Long excludeId,
            String message
    ) {

        for (ScheduleSession session : sessions) {

            if (excludeId != null
                    && session.getId().equals(excludeId)) {

                continue;
            }

            boolean overlap =
                    start.isBefore(
                            session.getEndTime()
                    )
                            &&
                            end.isAfter(
                                    session.getStartTime()
                            );

            if (overlap) {

                throw new ConflictException(
                        message
                );
            }
        }
    }
}