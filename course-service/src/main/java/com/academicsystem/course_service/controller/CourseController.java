package com.academicsystem.course_service.controller;

import com.academicsystem.course_service.dto.CourseResponse;
import com.academicsystem.course_service.dto.CreateCourseRequest;
import com.academicsystem.course_service.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @PostMapping
    public CourseResponse create(
            @Valid @RequestBody
            CreateCourseRequest request
    ) {
        return service.create(request);
    }

    @GetMapping
    public List<CourseResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CourseResponse findById(
            @PathVariable Long id
    ) {
        return service.findById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<CourseResponse> findByDepartment(
            @PathVariable Long departmentId
    ) {
        return service.findByDepartment(departmentId);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<CourseResponse> findByTeacher(
            @PathVariable Long teacherId
    ) {
        return service.findByTeacher(teacherId);
    }

    @PostMapping("/{courseId}/teachers/{teacherId}")
    public CourseResponse assignTeacher(
            @PathVariable Long courseId,
            @PathVariable Long teacherId
    ) {
        return service.assignTeacher(
                courseId,
                teacherId
        );
    }

    @DeleteMapping("/{courseId}/teachers/{teacherId}")
    public CourseResponse removeTeacher(
            @PathVariable Long courseId,
            @PathVariable Long teacherId
    ) {
        return service.removeTeacher(
                courseId,
                teacherId
        );
    }

    @PutMapping("/{id}")
    public CourseResponse update(
            @PathVariable Long id,
            @Valid @RequestBody
            CreateCourseRequest request
    ) {

        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        service.delete(id);
    }
}