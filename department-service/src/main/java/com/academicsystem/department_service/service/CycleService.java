package com.academicsystem.department_service.service;

import com.academicsystem.department_service.dto.CreateCycleRequest;
import com.academicsystem.department_service.dto.CycleResponse;

import java.util.List;

public interface CycleService {

    CycleResponse create(CreateCycleRequest request);

    List<CycleResponse> getAll();

    List<CycleResponse> getByProgram(Long programId);

    CycleResponse getById(Long id);

    CycleResponse update(
            Long id,
            CreateCycleRequest request
    );

    void delete(Long id);
}