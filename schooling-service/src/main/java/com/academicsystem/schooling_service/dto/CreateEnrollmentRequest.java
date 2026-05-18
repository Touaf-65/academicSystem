package com.academicsystem.schooling_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEnrollmentRequest(

        @NotNull
        Long studentId,

        @NotNull
        Long classRoomId,

        @NotBlank
        String academicYear
) {
}