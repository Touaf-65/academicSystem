package com.academicsystem.department_service.service;

import com.academicsystem.department_service.dto.CreateDepartmentRequest;
import com.academicsystem.department_service.dto.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse create(
            CreateDepartmentRequest request
    );

    DepartmentResponse getById(Long id);

    List<DepartmentResponse> getAll();

    DepartmentResponse update(
            Long id,
            CreateDepartmentRequest request
    );

    void delete(Long id);
}