package com.academicsystem.schooling_service.controller;

import com.academicsystem.schooling_service.dto.*;
import com.academicsystem.schooling_service.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PostMapping
    public StudentResponse create(
            @RequestBody
            @Valid
            CreateStudentRequest request
    ) {

        return studentService.create(request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER')")
    @GetMapping("/{id}")
    public StudentResponse getById(
            @PathVariable Long id
    ) {

        return studentService.getById(id);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping
    public List<StudentResponse> getAll() {

        return studentService.getAll();
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PutMapping("/{id}")
    public StudentResponse update(
            @PathVariable Long id,
            @RequestBody
            @Valid
            UpdateStudentRequest request
    ) {

        return studentService.update(id, request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {

        studentService.delete(id);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/department/{departmentId}")
    public List<StudentResponse> getByDepartment(
            @PathVariable Long departmentId
    ) {

        return studentService.getByDepartment(
                departmentId
        );
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/program/{programId}")
    public List<StudentResponse> getByProgram(
            @PathVariable Long programId
    ) {

        return studentService.getByProgram(
                programId
        );
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/cycle/{cycleId}")
    public List<StudentResponse> getByCycle(
            @PathVariable Long cycleId
    ) {

        return studentService.getByCycle(
                cycleId
        );
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/level/{levelId}")
    public List<StudentResponse> getByLevel(
            @PathVariable Long levelId
    ) {

        return studentService.getByLevel(
                levelId
        );
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @GetMapping("/class/{classId}")
    public List<StudentResponse> getByClass(
            @PathVariable Long classId
    ) {

        return studentService.getByClass(
                classId
        );
    }
}