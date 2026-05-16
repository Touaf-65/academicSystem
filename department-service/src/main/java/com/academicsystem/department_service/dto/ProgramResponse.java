package com.academicsystem.department_service.dto;

import lombok.Builder;

@Builder
public record ProgramResponse(

        Long id,
        String nom,
        String code,
        String description,
        Long departmentId
) {
}