package com.academicsystem.teacher_service.service;

import com.academicsystem.teacher_service.dto.CreateTeacherRequest;
import com.academicsystem.teacher_service.dto.TeacherResponse;

import java.util.List;

public interface TeacherService {

    TeacherResponse create(
            CreateTeacherRequest request
    );

    TeacherResponse getById(Long id);

    List<TeacherResponse> getAll();

    TeacherResponse update(
            Long id,
            CreateTeacherRequest request
    );

    void delete(Long id);
}