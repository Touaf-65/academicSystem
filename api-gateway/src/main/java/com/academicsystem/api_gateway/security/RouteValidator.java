package com.academicsystem.api_gateway.security;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RouteValidator {

    public static final Map<String, List<String>> ROLE_ACCESS = Map.of(

            "ADMIN_ONLY", List.of("ADMIN"),

            "TEACHER_ACCESS",
            List.of("ADMIN", "TEACHER"),

            "STUDENT_ACCESS",
            List.of(
                    "ADMIN",
                    "TEACHER",
                    "STUDENT"
            )
    );

    public boolean isAdminRoute(
            String path,
            HttpMethod method
    ) {

        return path.startsWith("/departments")
                && method != HttpMethod.GET;

    }

    public boolean isCourseAdminRoute(
            String path,
            HttpMethod method
    ) {

        return path.startsWith("/courses")
                && method != HttpMethod.GET;

    }

    public boolean isTeacherReadableRoute(
            String path,
            HttpMethod method
    ) {

        return path.startsWith("/courses")
                && method == HttpMethod.GET;
    }
}