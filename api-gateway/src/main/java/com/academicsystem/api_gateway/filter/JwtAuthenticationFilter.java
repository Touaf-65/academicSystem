package com.academicsystem.api_gateway.filter;

import com.academicsystem.api_gateway.security.JwtService;
import com.academicsystem.api_gateway.security.RouteValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter
        implements GlobalFilter, Ordered {

    private final JwtService jwtService;

    private final RouteValidator routeValidator;

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain
    ) {

        String path =
                exchange.getRequest()
                        .getURI()
                        .getPath();

        HttpMethod method =
                exchange.getRequest()
                        .getMethod();

        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION);

        // PUBLIC ROUTES

        if (path.startsWith("/auth/login")
                || path.startsWith("/auth/signup")
                || path.startsWith("/eureka")) {

            return chain.filter(exchange);
        }

        // NO TOKEN

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse()
                    .setComplete();
        }

        try {

            String token =
                    authHeader.substring(7);

            Claims claims =
                    jwtService.extractAllClaims(token);

            Long userId =
                    claims.get(
                            "userId",
                            Integer.class
                    ).longValue();

            String email =
                    claims.getSubject();

            String role =
                    claims.get(
                            "role",
                            String.class
                    );

            // ===== RBAC =====

            if (
                    routeValidator.isAdminRoute(
                            path,
                            method
                    )
                            && !role.equals("ADMIN")
            ) {

                exchange.getResponse()
                        .setStatusCode(
                                HttpStatus.FORBIDDEN
                        );

                return exchange.getResponse()
                        .setComplete();
            }

            if (
                    routeValidator.isCourseAdminRoute(
                            path,
                            method
                    )
                            && !role.equals("ADMIN")
            ) {

                exchange.getResponse()
                        .setStatusCode(
                                HttpStatus.FORBIDDEN
                        );

                return exchange.getResponse()
                        .setComplete();
            }

            HttpHeaders mutableHeaders =
                    new HttpHeaders();

            mutableHeaders.putAll(
                    exchange.getRequest()
                            .getHeaders()
            );

            mutableHeaders.set(
                    "X-User-Id",
                    userId.toString()
            );

            mutableHeaders.set(
                    "X-User-Email",
                    email
            );

            mutableHeaders.set(
                    "X-User-Role",
                    role
            );

            ServerHttpRequest request =
                    new ServerHttpRequestDecorator(
                            exchange.getRequest()
                    ) {
                        @Override
                        public HttpHeaders getHeaders() {
                            return mutableHeaders;
                        }
                    };

            return chain.filter(
                    exchange.mutate()
                            .request(request)
                            .build()
            );

        } catch (JwtException e) {

            exchange.getResponse()
                    .setStatusCode(
                            HttpStatus.UNAUTHORIZED
                    );

            return exchange.getResponse()
                    .setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}


//package com.academicsystem.api_gateway.filter;
//
//import com.academicsystem.api_gateway.security.JwtService;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter implements GlobalFilter, Ordered {
//
//    private final JwtService jwtService;
//
//    private static final List<String> PUBLIC_PATHS = List.of(
//            "/auth/login",
//            "/auth/signup",
//            "/eureka"
//    );
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        String path = exchange.getRequest().getURI().getPath();
//
//        boolean isPublic = PUBLIC_PATHS.stream()
//                .anyMatch(path::startsWith);
//
//        if (isPublic) {
//            return chain.filter(exchange);
//        }
//
//        String authHeader = exchange.getRequest()
//                .getHeaders()
//                .getFirst(HttpHeaders.AUTHORIZATION);
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//
//        try {
//            String token = authHeader.substring(7);
//            Claims claims = jwtService.extractAllClaims(token);
//
//            Long userId = claims.get("userId", Integer.class).longValue();
//            String email = claims.getSubject();
//            String role = claims.get("role", String.class);
//
//            HttpHeaders mutableHeaders = new HttpHeaders();
//            mutableHeaders.putAll(exchange.getRequest().getHeaders());
//            mutableHeaders.set("X-User-Id", userId.toString());
//            mutableHeaders.set("X-User-Email", email);
//            mutableHeaders.set("X-User-Role", role);
//
//            ServerHttpRequest request = new ServerHttpRequestDecorator(exchange.getRequest()) {
//                @Override
//                public HttpHeaders getHeaders() {
//                    return mutableHeaders;
//                }
//            };
//
//            return chain.filter(exchange.mutate().request(request).build());
//
//        } catch (JwtException e) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//    }
//
//    @Override
//    public int getOrder() {
//        return -1;
//    }
//}
