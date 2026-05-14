package com.academicsystem.infrastructure_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserContextFilter
        extends OncePerRequestFilter {

    private final UserContext userContext;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String userId =
                request.getHeader("X-User-Id");

        String role =
                request.getHeader("X-User-Role");

        String email =
                request.getHeader("X-User-Email");

        if (userId != null) {

            userContext.setUserId(
                    Long.parseLong(userId)
            );
        }

        userContext.setRole(role);

        userContext.setEmail(email);

        filterChain.doFilter(request, response);
    }
}