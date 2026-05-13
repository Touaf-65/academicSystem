package com.academicsystem.teacher_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateTeacherRequest(

        @NotBlank
        String nom,

        @Email
        String email,

        @NotBlank
        String password,

        List<Long> departmentIds
) {
}