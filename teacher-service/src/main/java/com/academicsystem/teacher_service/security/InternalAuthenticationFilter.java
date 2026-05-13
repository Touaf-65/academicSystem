package com.academicsystem.teacher_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class InternalAuthenticationFilter
        extends OncePerRequestFilter {

    @Value("${security.internal-secret}")
    private String internalSecret;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getRequestURI();

        /*
         * Ne protège QUE les endpoints internes
         */
        if (!path.startsWith("/internal")) {
            filterChain.doFilter(request, response);
            return;
        }

        String secret =
                request.getHeader("X-Internal-Secret");

        if (secret == null || !secret.equals(internalSecret)) {

            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            response.getWriter().write(
                    "Invalid internal secret"
            );

            return;
        }

        filterChain.doFilter(request, response);
    }
}