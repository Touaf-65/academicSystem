package com.academicsystem.department_service.controller;

import com.academicsystem.department_service.dto.CreateDepartmentRequest;
import com.academicsystem.department_service.dto.DepartmentResponse;
import com.academicsystem.department_service.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse create(
            @Valid @RequestBody CreateDepartmentRequest request
    ) {
        return departmentService.create(request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public DepartmentResponse getById(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping
    public List<DepartmentResponse> getAll() {
        return departmentService.getAll();
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @PutMapping("/{id}")
    public DepartmentResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CreateDepartmentRequest request
    ) {
        return departmentService.update(id, request);
    }

    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        departmentService.delete(id);
    }
}


//package com.academicsystem.department_service.controller;
//
//import com.academicsystem.department_service.dto.CreateDepartmentRequest;
//import com.academicsystem.department_service.dto.DepartmentResponse;
//import com.academicsystem.department_service.service.DepartmentService;
//
//import jakarta.validation.Valid;
//
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.http.HttpStatus;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/departments")
//@RequiredArgsConstructor
//public class DepartmentController {
//
//    private final DepartmentService service;
//
//    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public DepartmentResponse create(
//            @Valid @RequestBody
//            CreateDepartmentRequest request
//    ) {
//
//        return service.create(request);
//    }
//
//    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
//    @GetMapping("/{id}")
//    public DepartmentResponse getById(
//            @PathVariable Long id
//    ) {
//
//        return service.getById(id);
//    }
//
//    @PreAuthorize("@roleSecurity.hasRole('ADMIN', 'TEACHER', 'STUDENT')")
//    @GetMapping
//    public List<DepartmentResponse> getAll() {
//
//        return service.getAll();
//    }
//
//    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
//    @PutMapping("/{id}")
//    public DepartmentResponse update(
//            @PathVariable Long id,
//            @Valid @RequestBody
//            CreateDepartmentRequest request
//    ) {
//
//        return service.update(id, request);
//    }
//
//    @PreAuthorize("@roleSecurity.hasRole('ADMIN')")
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(
//            @PathVariable Long id
//    ) {
//
//        service.delete(id);
//    }
//}