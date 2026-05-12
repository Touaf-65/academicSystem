package com.academicsystem.auth_service.dto;

import com.academicsystem.auth_service.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequest(

        @Email
        String email,

        @NotBlank
        String password,

        Role role
) {
}