package com.academicsystem.teacher_service.dto.auth;

public record CreateAccountRequest(

        String email,

        String password,

        String role
) {
}