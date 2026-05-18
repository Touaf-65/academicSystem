package com.academicsystem.schooling_service.dto;

public record StudentResponse(

        Long id,

        String nom,

        String email,

        String matricule,

        Long authUserId
) {
}