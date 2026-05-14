package com.academicsystem.infrastructure_service.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@Setter
@Component
@RequestScope
public class UserContext {

    private Long userId;

    private String role;

    private String email;
}