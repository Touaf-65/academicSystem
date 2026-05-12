package com.academicsystem.auth_service.dto;

public record CreateAccountResponse(
        Long id,
        String email,
        String role
) {
}