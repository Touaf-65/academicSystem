package com.academicsystem.department_service.controller;

import com.academicsystem.department_service.dto.CreateProgramRequest;
import com.academicsystem.department_service.dto.ProgramResponse;
import com.academicsystem.department_service.service.ProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramResponse create(
            @Valid @RequestBody CreateProgramRequest request
    ) {
        return programService.create(request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public ProgramResponse getById(@PathVariable Long id) {
        return programService.getById(id);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping
    public List<ProgramResponse> getAll() {
        return programService.getAll();
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping("/department/{departmentId}")
    public List<ProgramResponse> getByDepartment(
            @PathVariable Long departmentId
    ) {
        return programService.getByDepartment(departmentId);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ProgramResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CreateProgramRequest request
    ) {
        return programService.update(id, request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        programService.delete(id);
    }
}