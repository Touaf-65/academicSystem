package com.academicsystem.infrastructure_service.service;

import com.academicsystem.infrastructure_service.dto.BuildingResponse;
import com.academicsystem.infrastructure_service.dto.CreateBuildingRequest;

import java.util.List;

public interface BuildingService {

    BuildingResponse create(CreateBuildingRequest request);

    List<BuildingResponse> getAll();

    BuildingResponse getById(Long id);

    BuildingResponse update(Long id, CreateBuildingRequest request);

    void delete(Long id);
}