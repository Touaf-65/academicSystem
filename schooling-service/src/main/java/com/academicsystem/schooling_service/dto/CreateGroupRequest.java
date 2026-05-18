package com.academicsystem.schooling_service.dto;

import com.academicsystem.schooling_service.enums.GroupType;
import jakarta.validation.constraints.NotBlank;

public record CreateGroupRequest(

        @NotBlank
        String nom,

        Long classRoomId,

        GroupType type,

        Integer capaciteMax
) {
}
