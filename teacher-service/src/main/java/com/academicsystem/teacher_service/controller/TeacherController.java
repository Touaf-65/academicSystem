package com.academicsystem.teacher_service.controller;

import com.academicsystem.teacher_service.dto.CreateTeacherRequest;
import com.academicsystem.teacher_service.dto.TeacherResponse;
import com.academicsystem.teacher_service.service.TeacherService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponse create(
            @Valid @RequestBody
            CreateTeacherRequest request
    ) {

        return service.create(request);
    }

    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN','TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public TeacherResponse getById(
            @PathVariable Long id
    ) {

        return service.getById(id);
    }

    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN','TEACHER')")
    @GetMapping
    public List<TeacherResponse> getAll() {

        return service.getAll();
    }

    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public TeacherResponse update(
            @PathVariable Long id,
            @Valid @RequestBody
            CreateTeacherRequest request
    ) {

        return service.update(id, request);
    }

    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ) {

        service.delete(id);
    }
}