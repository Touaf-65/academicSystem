package com.academicsystem.schooling_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public record CreateStudentRequest<academicYear>(

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @Email
        String email,

        @NotBlank
        String password,

        @NotNull
        Long classId,

        @NotBlank
        String academicYear
) {
}