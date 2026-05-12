package com.academicsystem.teacher_service.dto;

import java.util.List;

public record TeacherResponse(

        Long id,

        String nom,

        String email,

        List<Long> departmentIds,

        List<Long> courseIds
) {
}