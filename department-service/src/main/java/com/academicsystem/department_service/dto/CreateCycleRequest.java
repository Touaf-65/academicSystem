package com.academicsystem.department_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateCycleRequest(

        @NotBlank
        String nom,

        @NotBlank
        String code,

        @Positive
        Integer dureeAnnees,

        @NotNull
        Long programId
) {
}