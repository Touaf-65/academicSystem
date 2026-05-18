package com.academicsystem.schooling_service.dto.auth;

public record CreateAccountResponse(

        Long id,

        String email,

        String role
) {
}