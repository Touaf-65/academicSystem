package com.academicsystem.auth_service.dto;

import com.academicsystem.auth_service.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;
}