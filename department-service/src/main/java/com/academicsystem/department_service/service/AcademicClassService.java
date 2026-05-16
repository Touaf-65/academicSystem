package com.academicsystem.department_service.service;

import com.academicsystem.department_service.dto.CreateAcademicClassRequest;
import com.academicsystem.department_service.dto.AcademicClassResponse;

import java.util.List;

public interface AcademicClassService {

    AcademicClassResponse create(
            CreateAcademicClassRequest request
    );

    List<AcademicClassResponse> getAll();

    List<AcademicClassResponse> getByLevel(Long levelId);

    AcademicClassResponse getById(Long id);

    AcademicClassResponse update(
            Long id,
            CreateAcademicClassRequest request
    );

    void delete(Long id);
}