package com.academicsystem.infrastructure_service.dto;

import com.academicsystem.infrastructure_service.enums.RoomType;
import jakarta.validation.constraints.NotBlank;

public record CreateRoomRequest(

        @NotBlank
        String nom,

        Integer capacite,

        RoomType type,

        Long floorId
) {
}
