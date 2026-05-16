package com.academicsystem.department_service.dto;

import lombok.Builder;

@Builder
public record AcademicClassResponse(

        Long id,
        String nom,
        String code,
        Integer capacite,
        Long levelId
) {
}