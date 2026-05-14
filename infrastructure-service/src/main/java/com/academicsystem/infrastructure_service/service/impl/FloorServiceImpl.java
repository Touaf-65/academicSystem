package com.academicsystem.infrastructure_service.service.impl;

import com.academicsystem.infrastructure_service.dto.CreateFloorRequest;
import com.academicsystem.infrastructure_service.dto.FloorResponse;
import com.academicsystem.infrastructure_service.entity.Building;
import com.academicsystem.infrastructure_service.entity.Floor;
import com.academicsystem.infrastructure_service.repository.BuildingRepository;
import com.academicsystem.infrastructure_service.repository.FloorRepository;
import com.academicsystem.infrastructure_service.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FloorServiceImpl
        implements FloorService {

    private final FloorRepository floorRepository;

    private final BuildingRepository buildingRepository;

    @Override
    public FloorResponse create(
            CreateFloorRequest request
    ) {

        Building building =
                buildingRepository.findById(
                                request.buildingId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Building not found"
                                ));

        int usedCapacity = building.getFloors()
                .stream()
                .mapToInt(Floor::getCapacite)
                .sum();

        if (
                usedCapacity + request.capacite()
                        > building.getCapaciteTotale()
        ) {

            throw new RuntimeException(
                    "Building capacity exceeded"
            );
        }

        Floor floor = Floor.builder()
                .numero(request.numero())
                .capacite(request.capacite())
                .building(building)
                .build();

        Floor saved =
                floorRepository.save(floor);

        return mapToResponse(saved);
    }

    @Override
    public FloorResponse getById(Long id) {

        Floor floor =
                floorRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Floor not found"
                                ));

        return mapToResponse(floor);
    }

    @Override
    public List<FloorResponse> getAll() {

        return floorRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public FloorResponse update(
            Long id,
            CreateFloorRequest request
    ) {

        Floor floor =
                floorRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Floor not found"
                                ));

        Building building =
                buildingRepository.findById(
                                request.buildingId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Building not found"
                                ));

        int usedCapacity = building.getFloors()
                .stream()
                .filter(f -> !f.getId().equals(id))
                .mapToInt(Floor::getCapacite)
                .sum();

        if (
                usedCapacity + request.capacite()
                        > building.getCapaciteTotale()
        ) {

            throw new RuntimeException(
                    "Building capacity exceeded"
            );
        }

        floor.setNumero(request.numero());
        floor.setCapacite(request.capacite());
        floor.setBuilding(building);

        Floor updated =
                floorRepository.save(floor);

        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {

        Floor floor =
                floorRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Floor not found"
                                ));

        if (
                floor.getRooms() != null
                        && !floor.getRooms().isEmpty()
        ) {

            throw new RuntimeException(
                    "Cannot delete floor with rooms"
            );
        }

        floorRepository.delete(floor);
    }

    @Override
    public List<FloorResponse> getByBuilding(
            Long buildingId
    ) {

        return floorRepository.findByBuildingId(
                        buildingId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private FloorResponse mapToResponse(
            Floor floor
    ) {

        return FloorResponse.builder()
                .id(floor.getId())
                .numero(floor.getNumero())
                .capacite(floor.getCapacite())
                .buildingId(
                        floor.getBuilding().getId()
                )
                .build();
    }
}