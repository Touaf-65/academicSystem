package com.academicsystem.department_service.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateDepartmentRequest(

        @NotBlank(message = "Le nom est obligatoire")
        String nom,

        String description
) {
}