package com.academicsystem.planning_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "course-service")
public interface CourseClient {

    @GetMapping("/courses/{id}")
    Object getCourseById(
            @PathVariable Long id
    );
}