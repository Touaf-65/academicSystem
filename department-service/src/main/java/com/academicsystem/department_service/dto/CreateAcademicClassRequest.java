package com.academicsystem.department_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateAcademicClassRequest(

        @NotBlank
        String nom,

        @NotBlank
        String code,

        @Positive
        Integer capacite,

        @NotNull
        Long levelId
) {
}