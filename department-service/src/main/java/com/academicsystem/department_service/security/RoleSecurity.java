package com.academicsystem.department_service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("roleSecurity")
@RequiredArgsConstructor
public class RoleSecurity {

    private final UserContext userContext;

    public boolean hasRole(String role) {

        return role.equals(userContext.getRole());
    }

    public boolean hasAnyRole(String... roles) {

        if (userContext.getRole() == null) {
            return false;
        }

        for (String role : roles) {

            if (role.equals(userContext.getRole())) {
                return true;
            }
        }

        return false;
    }
}