package com.academicsystem.planning_service.repository;

import com.academicsystem.planning_service.entity.ScheduleSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository
        extends JpaRepository<ScheduleSession, Long> {

    List<ScheduleSession> findByTeacherId(
            Long teacherId
    );

    List<ScheduleSession> findByRoomId(
            Long roomId
    );

    List<ScheduleSession> findByClassId(
            Long classId
    );

    List<ScheduleSession> findByGroupId(
            Long groupId
    );

    List<ScheduleSession> findByDayOfWeek(
            DayOfWeek day
    );

    List<ScheduleSession>
    findByRoomIdAndDayOfWeek(
            Long roomId,
            DayOfWeek day
    );

    List<ScheduleSession>
    findByTeacherIdAndDayOfWeek(
            Long teacherId,
            DayOfWeek day
    );

    List<ScheduleSession>
    findByClassIdAndDayOfWeek(
            Long classId,
            DayOfWeek day
    );

    List<ScheduleSession>
    findByGroupIdAndDayOfWeek(
            Long groupId,
            DayOfWeek day
    );
}