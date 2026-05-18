package com.academicsystem.schooling_service.dto;

public record AcademicClassResponse(

        Long id,

        String nom,

        Integer capacite,

        Long levelId
) {
}