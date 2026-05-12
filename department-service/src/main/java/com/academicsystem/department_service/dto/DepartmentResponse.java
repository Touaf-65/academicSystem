package com.academicsystem.department_service.dto;

public record DepartmentResponse(

        Long id,
        String nom,
        String description
) {
}