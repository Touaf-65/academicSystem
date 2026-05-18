package com.academicsystem.schooling_service.client;

import com.academicsystem.schooling_service.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "department-service")
public interface DepartmentClient {

    @GetMapping("/classrooms/{id}")
    AcademicClassResponse getClassById(
            @PathVariable Long id
    );

    @GetMapping("/academic-levels/{id}")
    LevelResponse getLevelById(
            @PathVariable Long id
    );

    @GetMapping("/cycles/{id}")
    CycleResponse getCycleById(
            @PathVariable Long id
    );

    @GetMapping("/programs/{id}")
    ProgramResponse getProgramById(
            @PathVariable Long id
    );

    @GetMapping("/departments/{id}")
    DepartmentResponse getDepartmentById(
            @PathVariable Long id
    );
}