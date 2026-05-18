package com.academicsystem.schooling_service.controller;

import com.academicsystem.schooling_service.dto.*;
import com.academicsystem.schooling_service.enums.EnrollmentStatus;
import com.academicsystem.schooling_service.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PostMapping
    public EnrollmentResponse create(
            @RequestBody
            @Valid
            CreateEnrollmentRequest request
    ) {

        return enrollmentService.create(request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/{id}")
    public EnrollmentResponse getById(
            @PathVariable Long id
    ) {

        return enrollmentService.getById(id);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping
    public List<EnrollmentResponse> getAll() {

        return enrollmentService.getAll();
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/student/{studentId}")
    public List<EnrollmentResponse> getByStudent(
            @PathVariable Long studentId
    ) {

        return enrollmentService.getByStudent(
                studentId
        );
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/class/{classId}")
    public List<EnrollmentResponse> getByClass(
            @PathVariable Long classId
    ) {

        return enrollmentService.getByClass(
                classId
        );
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/department/{departmentId}")
    public List<EnrollmentResponse> getByDepartment(
            @PathVariable Long departmentId
    ) {

        return enrollmentService.getByDepartment(
                departmentId
        );
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/program/{programId}")
    public List<EnrollmentResponse> getByProgram(
            @PathVariable Long programId
    ) {

        return enrollmentService.getByProgram(
                programId
        );
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/cycle/{cycleId}")
    public List<EnrollmentResponse> getByCycle(
            @PathVariable Long cycleId
    ) {

        return enrollmentService.getByCycle(
                cycleId
        );
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/level/{levelId}")
    public List<EnrollmentResponse> getByLevel(
            @PathVariable Long levelId
    ) {

        return enrollmentService.getByLevel(
                levelId
        );
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PatchMapping("/{id}/status")
    public EnrollmentResponse updateStatus(
            @PathVariable Long id,
            @RequestParam EnrollmentStatus status
    ) {

        return enrollmentService.updateStatus(
                id,
                status
        );
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {

        enrollmentService.delete(id);
    }
}