package com.academicsystem.planning_service.service.impl;

import com.academicsystem.planning_service.client.*;
import com.academicsystem.planning_service.dto.*;
import com.academicsystem.planning_service.entity.ScheduleSession;
import com.academicsystem.planning_service.exception.ResourceNotFoundException;
import com.academicsystem.planning_service.repository.ScheduleRepository;
import com.academicsystem.planning_service.service.ConflictDetectionService;
import com.academicsystem.planning_service.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl
        implements ScheduleService {

    private final ScheduleRepository repository;

    private final ConflictDetectionService conflictService;

    private final DepartmentClient departmentClient;

    private final TeacherClient teacherClient;

    private final CourseClient courseClient;

    private final InfrastructureClient infrastructureClient;

    private final SchoolingClient schoolingClient;

    @Override
    public ScheduleResponse create(
            CreateScheduleRequest request
    ) {

        // ===============================
        // VALIDATE REFERENCES
        // ===============================

        departmentClient.getClassById(
                request.classId()
        );

        teacherClient.getTeacherById(
                request.teacherId()
        );

        courseClient.getCourseById(
                request.courseId()
        );

        infrastructureClient.getRoomById(
                request.roomId()
        );

        if (request.groupId() != null) {

            schoolingClient.getGroupById(
                    request.groupId()
            );
        }

        // ===============================
        // DETECT CONFLICTS
        // ===============================

        conflictService.checkRoomConflict(
                request.roomId(),
                request.dayOfWeek(),
                request.startTime(),
                request.endTime(),
                null
        );

        conflictService.checkTeacherConflict(
                request.teacherId(),
                request.dayOfWeek(),
                request.startTime(),
                request.endTime(),
                null
        );

        conflictService.checkClassConflict(
                request.classId(),
                request.dayOfWeek(),
                request.startTime(),
                request.endTime(),
                null
        );

        conflictService.checkGroupConflict(
                request.groupId(),
                request.dayOfWeek(),
                request.startTime(),
                request.endTime(),
                null
        );

        // ===============================
        // CREATE
        // ===============================

        ScheduleSession session =
                ScheduleSession.builder()
                        .classId(request.classId())
                        .groupId(request.groupId())
                        .courseId(request.courseId())
                        .teacherId(request.teacherId())
                        .roomId(request.roomId())
                        .dayOfWeek(request.dayOfWeek())
                        .startTime(request.startTime())
                        .endTime(request.endTime())
                        .semester(request.semester())
                        .academicYear(request.academicYear())
                        .sessionType(request.sessionType())
                        .cancelled(false)
                        .build();

        return map(
                repository.save(session)
        );
    }

    @Override
    public ScheduleResponse update(
            Long id,
            UpdateScheduleRequest request
    ) {

        ScheduleSession session =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Session not found"
                                ));

        conflictService.checkRoomConflict(
                request.roomId(),
                request.dayOfWeek(),
                request.startTime(),
                request.endTime(),
                id
        );

        conflictService.checkTeacherConflict(
                request.teacherId(),
                request.dayOfWeek(),
                request.startTime(),
                request.endTime(),
                id
        );

        conflictService.checkClassConflict(
                request.classId(),
                request.dayOfWeek(),
                request.startTime(),
                request.endTime(),
                id
        );

        conflictService.checkGroupConflict(
                request.groupId(),
                request.dayOfWeek(),
                request.startTime(),
                request.endTime(),
                id
        );

        session.setClassId(request.classId());
        session.setGroupId(request.groupId());
        session.setCourseId(request.courseId());
        session.setTeacherId(request.teacherId());
        session.setRoomId(request.roomId());
        session.setDayOfWeek(request.dayOfWeek());
        session.setStartTime(request.startTime());
        session.setEndTime(request.endTime());
        session.setSemester(request.semester());
        session.setAcademicYear(
                request.academicYear()
        );
        session.setSessionType(
                request.sessionType()
        );
        session.setCancelled(
                request.cancelled()
        );

        return map(
                repository.save(session)
        );
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);
    }

    @Override
    public ScheduleResponse getById(Long id) {

        return map(
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Session not found"
                                ))
        );
    }

    @Override
    public List<ScheduleResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<ScheduleResponse> getByTeacher(
            Long teacherId
    ) {

        return repository.findByTeacherId(
                        teacherId
                )
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<ScheduleResponse> getByRoom(
            Long roomId
    ) {

        return repository.findByRoomId(
                        roomId
                )
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<ScheduleResponse> getByClass(
            Long classId
    ) {

        return repository.findByClassId(
                        classId
                )
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<ScheduleResponse> getByGroup(
            Long groupId
    ) {

        return repository.findByGroupId(
                        groupId
                )
                .stream()
                .map(this::map)
                .toList();
    }

    // =====================================================
    // MAPPER
    // =====================================================

    private ScheduleResponse map(
            ScheduleSession session
    ) {

        return new ScheduleResponse(
                session.getId(),
                session.getClassId(),
                session.getGroupId(),
                session.getCourseId(),
                session.getTeacherId(),
                session.getRoomId(),
                session.getDayOfWeek(),
                session.getStartTime(),
                session.getEndTime(),
                session.getSemester(),
                session.getAcademicYear(),
                session.getSessionType(),
                session.getCancelled()
        );
    }
}