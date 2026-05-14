package com.academicsystem.infrastructure_service.dto;

public record CreateFloorRequest(

        Integer numero,

        Integer capacite,

        Long buildingId
) {
}