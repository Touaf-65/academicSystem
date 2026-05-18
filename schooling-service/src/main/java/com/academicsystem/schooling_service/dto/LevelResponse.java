package com.academicsystem.schooling_service.dto;

public record LevelResponse(

        Long id,

        String nom,

        Integer ordre,

        Long cycleId
) {
}