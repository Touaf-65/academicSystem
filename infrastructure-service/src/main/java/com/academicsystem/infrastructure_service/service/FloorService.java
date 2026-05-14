package com.academicsystem.infrastructure_service.service;

import com.academicsystem.infrastructure_service.dto.CreateFloorRequest;
import com.academicsystem.infrastructure_service.dto.FloorResponse;
import com.academicsystem.infrastructure_service.entity.Floor;

import java.util.List;

public interface FloorService {

    FloorResponse create(CreateFloorRequest request);

    FloorResponse getById(Long id);

    FloorResponse update(
            Long id,
            CreateFloorRequest request
    );

    void delete(Long id);

    List<FloorResponse> getAll();

    List<FloorResponse> getByBuilding(Long buildingId);
}