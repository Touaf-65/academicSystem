package com.academicsystem.department_service.dto;

import lombok.Builder;

@Builder
public record AcademicLevelResponse(

        Long id,
        String nom,
        String code,
        Integer ordre,
        Long cycleId
) {
}