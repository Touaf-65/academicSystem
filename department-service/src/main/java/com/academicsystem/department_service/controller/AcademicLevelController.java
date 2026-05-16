package com.academicsystem.department_service.controller;

import com.academicsystem.department_service.dto.CreateAcademicLevelRequest;
import com.academicsystem.department_service.dto.AcademicLevelResponse;
import com.academicsystem.department_service.service.AcademicLevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic-levels")
@RequiredArgsConstructor
public class AcademicLevelController {

    private final AcademicLevelService academicLevelService;

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AcademicLevelResponse create(
            @Valid @RequestBody CreateAcademicLevelRequest request
    ) {
        return academicLevelService.create(request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public AcademicLevelResponse getById(@PathVariable Long id) {
        return academicLevelService.getById(id);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping
    public List<AcademicLevelResponse> getAll() {
        return academicLevelService.getAll();
    }

//    @GetMapping("/program/{programId}")
//    public List<AcademicLevelResponse> getByProgram(
//            @PathVariable Long programId
//    ) {
//        return academicLevelService.getByProgram(programId);
//    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PutMapping("/{id}")
    public AcademicLevelResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CreateAcademicLevelRequest request
    ) {
        return academicLevelService.update(id, request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        academicLevelService.delete(id);
    }
}