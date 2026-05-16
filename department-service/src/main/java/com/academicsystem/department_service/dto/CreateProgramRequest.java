package com.academicsystem.department_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProgramRequest(

        @NotBlank
        String nom,

        @NotBlank
        String code,

        String description,

        @NotNull
        Long departmentId
) {
}