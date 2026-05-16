package com.academicsystem.department_service.controller;

import com.academicsystem.department_service.dto.CreateAcademicClassRequest;
import com.academicsystem.department_service.dto.AcademicClassResponse;
import com.academicsystem.department_service.service.AcademicClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class AcademicClassController {

    private final AcademicClassService academicClassService;

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AcademicClassResponse create(
            @Valid @RequestBody CreateAcademicClassRequest request
    ) {
        return academicClassService.create(request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public AcademicClassResponse getById(@PathVariable Long id) {
        return academicClassService.getById(id);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping
    public List<AcademicClassResponse> getAll() {
        return academicClassService.getAll();
    }

//    @GetMapping("/academic-level/{academicLevelId}")
//    public List<AcademicClassResponse> getByAcademicLevel(
//            @PathVariable Long academicLevelId
//    ) {
//        return academicClassService.getByAcademicLevel(academicLevelId);
//    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PutMapping("/{id}")
    public AcademicClassResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CreateAcademicClassRequest request
    ) {
        return academicClassService.update(id, request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        academicClassService.delete(id);
    }
}