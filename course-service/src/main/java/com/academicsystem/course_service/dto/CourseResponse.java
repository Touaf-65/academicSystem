package com.academicsystem.course_service.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CourseResponse(

        Long id,

        String name,

        String code,

        Integer volumeHoraire,

        Long departmentId,

        List<Long> teacherIds
) {
}