package com.academicsystem.course_service.client;

import com.academicsystem.course_service.client.dto.TeacherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "teacher-service")
public interface TeacherClient {

    @GetMapping("/teachers/{id}")
    TeacherResponse findById(
            @PathVariable Long id
    );
}