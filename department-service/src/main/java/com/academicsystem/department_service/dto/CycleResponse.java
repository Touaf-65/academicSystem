package com.academicsystem.department_service.dto;

import lombok.Builder;

@Builder
public record CycleResponse(

        Long id,
        String nom,
        String code,
        Integer dureeAnnees,
        Long programId
) {
}