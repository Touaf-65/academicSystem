package com.academicsystem.department_service.controller;

import com.academicsystem.department_service.dto.CreateCycleRequest;
import com.academicsystem.department_service.dto.CycleResponse;
import com.academicsystem.department_service.service.CycleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cycles")
@RequiredArgsConstructor
public class CycleController {

    private final CycleService cycleService;

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CycleResponse create(
            @RequestBody @Valid CreateCycleRequest request
    ) {
        return cycleService.create(request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public CycleResponse getById(
            @PathVariable Long id
    ) {
        return cycleService.getById(id);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping
    public List<CycleResponse> getAll() {
        return cycleService.getAll();
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping("/program/{programId}")
    public List<CycleResponse> getByProgram(
            @PathVariable Long programId
    ) {
        return cycleService.getByProgram(programId);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CycleResponse update(
            @PathVariable Long id,
            @RequestBody @Valid CreateCycleRequest request
    ) {
        return cycleService.update(id, request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ) {
        cycleService.delete(id);
    }
}