package com.academicsystem.infrastructure_service.dto;

import lombok.Builder;

@Builder
public record BuildingResponse(

        Long id,

        String nom,

        String description,

        Integer capaciteTotale
) {
}