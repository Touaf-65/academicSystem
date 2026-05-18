package com.academicsystem.schooling_service.dto;

import com.academicsystem.schooling_service.enums.EnrollmentStatus;
import lombok.Builder;

@Builder
public record EnrollmentResponse(

        Long id,

        Long studentId,

        Long classRoomId,

        String academicYear,

        EnrollmentStatus status
) {
}