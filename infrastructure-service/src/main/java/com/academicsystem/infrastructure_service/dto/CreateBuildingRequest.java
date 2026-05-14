package com.academicsystem.infrastructure_service.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateBuildingRequest(

        @NotBlank
        String nom,

        String description,

        Integer capaciteTotale
) {
}
