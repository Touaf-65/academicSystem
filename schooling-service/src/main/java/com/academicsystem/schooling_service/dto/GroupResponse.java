package com.academicsystem.schooling_service.dto;

import com.academicsystem.schooling_service.enums.GroupType;
import lombok.Builder;

import java.util.List;

@Builder
public record GroupResponse(

        Long id,

        String nom,

        GroupType type,

        Integer capaciteMax,

        List<Long> studentIds
) {
}