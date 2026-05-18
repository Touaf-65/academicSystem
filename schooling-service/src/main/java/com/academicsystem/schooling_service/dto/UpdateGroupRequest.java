package com.academicsystem.schooling_service.dto;

import com.academicsystem.schooling_service.enums.GroupType;

public record UpdateGroupRequest(

        String nom,

        GroupType type,

        Integer capaciteMax
) {
}
