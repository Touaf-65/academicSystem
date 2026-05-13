package com.academicsystem.department_service.controller;

import com.academicsystem.department_service.dto.CreateDepartmentRequest;
import com.academicsystem.department_service.dto.DepartmentResponse;
import com.academicsystem.department_service.service.DepartmentService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/departments")
@RequiredArgsConstructor
public class InternalDepartmentController {

    private final DepartmentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse create(
            @Valid @RequestBody
            CreateDepartmentRequest request
    ) {

        return service.create(request);
    }

    @GetMapping("/{id}")
    public DepartmentResponse getById(
            @PathVariable Long id
    ) {

        return service.getById(id);
    }

    @GetMapping
    public List<DepartmentResponse> getAll() {

        return service.getAll();
    }

    @PutMapping("/{id}")
    public DepartmentResponse update(
            @PathVariable Long id,
            @Valid @RequestBody
            CreateDepartmentRequest request
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