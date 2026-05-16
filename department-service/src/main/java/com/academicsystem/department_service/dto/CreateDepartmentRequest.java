package com.academicsystem.department_service.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateDepartmentRequest(

        @NotBlank
        String nom,

        String description
) {
}