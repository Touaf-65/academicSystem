package com.academicsystem.infrastructure_service.dto;

import lombok.Builder;

@Builder
public record FloorResponse(

        Long id,

        Integer numero,

        Integer capacite,

        Long buildingId
) {
}