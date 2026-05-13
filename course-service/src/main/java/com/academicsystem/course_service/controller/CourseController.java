package com.academicsystem.course_service.controller;

import com.academicsystem.course_service.dto.CourseResponse;
import com.academicsystem.course_service.dto.CreateCourseRequest;
import com.academicsystem.course_service.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PostMapping
    public CourseResponse create(
            @Valid @RequestBody
            CreateCourseRequest request
    ) {
        return service.create(request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping
    public List<CourseResponse> findAll() {
        return service.findAll();
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public CourseResponse findById(
            @PathVariable Long id
    ) {
        return service.findById(id);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping("/department/{departmentId}")
    public List<CourseResponse> findByDepartment(
            @PathVariable Long departmentId
    ) {
        return service.findByDepartment(departmentId);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping("/teacher/{teacherId}")
    public List<CourseResponse> findByTeacher(
            @PathVariable Long teacherId
    ) {
        return service.findByTeacher(teacherId);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
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

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
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

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CourseResponse update(
            @PathVariable Long id,
            @Valid @RequestBody
            CreateCourseRequest request
    ) {

        return service.update(id, request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        service.delete(id);
    }
}