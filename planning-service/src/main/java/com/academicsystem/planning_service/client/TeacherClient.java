package com.academicsystem.planning_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "teacher-service")
public interface TeacherClient {

    @GetMapping("/teachers/{id}")
    Object getTeacherById(
            @PathVariable Long id
    );
}