package com.academicsystem.course_service.controller;

import com.academicsystem.course_service.dto.CreateCourseRequest;
import com.academicsystem.course_service.dto.CourseResponse;
import com.academicsystem.course_service.service.CourseService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/departments")
@RequiredArgsConstructor
public class InternalCourseController {

    private final CourseService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse create(
            @Valid @RequestBody
            CreateCourseRequest request
    ) {

        return service.create(request);
    }

    @GetMapping("/{id}")
    public CourseResponse getById(
            @PathVariable Long id
    ) {

        return service.findById(id);
    }

    @GetMapping
    public List<CourseResponse> getAll() {

        return service.findAll();
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ) {

        service.delete(id);
    }
}