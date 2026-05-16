package com.academicsystem.department_service.service;

import com.academicsystem.department_service.dto.CreateProgramRequest;
import com.academicsystem.department_service.dto.ProgramResponse;

import java.util.List;

public interface ProgramService {

    ProgramResponse create(CreateProgramRequest request);

    List<ProgramResponse> getAll();

    List<ProgramResponse> getByDepartment(Long departmentId);

    ProgramResponse getById(Long id);

    ProgramResponse update(
            Long id,
            CreateProgramRequest request
    );

    void delete(Long id);
}