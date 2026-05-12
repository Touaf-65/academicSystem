package com.academicsystem.course_service.client;

import com.academicsystem.course_service.client.dto.DepartmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service")
public interface DepartmentClient {

    @GetMapping("/departments/{id}")
    DepartmentResponse findById(
            @PathVariable Long id
    );
}