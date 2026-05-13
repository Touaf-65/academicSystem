package com.academicsystem.teacher_service.dto.auth;

public record CreateAccountResponse(

        Long id,

        String email,

        String role
) {
}