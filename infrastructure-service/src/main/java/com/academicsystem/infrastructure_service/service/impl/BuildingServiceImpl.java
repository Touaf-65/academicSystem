package com.academicsystem.infrastructure_service.service.impl;

import com.academicsystem.infrastructure_service.dto.CreateBuildingRequest;
import com.academicsystem.infrastructure_service.dto.BuildingResponse;
import com.academicsystem.infrastructure_service.entity.Building;
import com.academicsystem.infrastructure_service.repository.BuildingRepository;
import com.academicsystem.infrastructure_service.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl
        implements BuildingService {

    private final BuildingRepository buildingRepository;

    @Override
    public BuildingResponse create(
            CreateBuildingRequest request
    ) {

        Building building = Building.builder()
                .nom(request.nom())
                .description(request.description())
                .capaciteTotale(request.capaciteTotale())
                .build();

        Building saved =
                buildingRepository.save(building);

        return mapToResponse(saved);
    }

    @Override
    public List<BuildingResponse> getAll() {

        return buildingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BuildingResponse getById(Long id) {

        Building building =
                buildingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Building not found"
                                ));

        return mapToResponse(building);
    }

    @Override
    public BuildingResponse update(
            Long id,
            CreateBuildingRequest request
    ) {
        Building building = buildingRepository.findById(id)
                .orElseThrow(() ->
                            new  RuntimeException(
                                    "Building not found"
                            )
                );
        building.setNom(request.nom());
        building.setDescription(request.description());
        building.setCapaciteTotale(request.capaciteTotale());

        Building updated = buildingRepository.save(building);
        return mapToResponse(updated);
    };

    @Override
    public void delete(Long id) {

        Building building =
                buildingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Building not found"
                                ));

        if (
                building.getFloors() != null
                        && !building.getFloors().isEmpty()
        ) {

            throw new RuntimeException(
                    "Cannot delete building with floors"
            );
        }

        buildingRepository.delete(building);
    }

    private BuildingResponse mapToResponse(
            Building building
    ) {

        return BuildingResponse.builder()
                .id(building.getId())
                .nom(building.getNom())
                .description(building.getDescription())
                .capaciteTotale(
                        building.getCapaciteTotale()
                )
                .build();
    }
}