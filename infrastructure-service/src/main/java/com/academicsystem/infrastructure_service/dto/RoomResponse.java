package com.academicsystem.infrastructure_service.dto;

import com.academicsystem.infrastructure_service.enums.RoomType;
import lombok.Builder;

@Builder
public record RoomResponse(

        Long id,

        String nom,

        Integer capacite,

        RoomType type,

        Long floorId
) {
}