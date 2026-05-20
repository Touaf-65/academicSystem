package com.academicsystem.planning_service.service;

import com.academicsystem.planning_service.dto.*;

import java.util.List;

public interface ScheduleService {

    ScheduleResponse create(
            CreateScheduleRequest request
    );

    ScheduleResponse update(
            Long id,
            UpdateScheduleRequest request
    );

    void delete(Long id);

    ScheduleResponse getById(Long id);

    List<ScheduleResponse> getAll();

    List<ScheduleResponse> getByTeacher(
            Long teacherId
    );

    List<ScheduleResponse> getByRoom(
            Long roomId
    );

    List<ScheduleResponse> getByClass(
            Long classId
    );

    List<ScheduleResponse> getByGroup(
            Long groupId
    );
}