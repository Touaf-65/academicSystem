package com.academicsystem.department_service.service;

import com.academicsystem.department_service.dto.CreateAcademicLevelRequest;
import com.academicsystem.department_service.dto.AcademicLevelResponse;

import java.util.List;

public interface AcademicLevelService {

    AcademicLevelResponse create(
            CreateAcademicLevelRequest request
    );

    List<AcademicLevelResponse> getAll();

    List<AcademicLevelResponse> getByCycle(Long cycleId);

    AcademicLevelResponse getById(Long id);

    AcademicLevelResponse update(
            Long id,
            CreateAcademicLevelRequest request
    );

    void delete(Long id);
}