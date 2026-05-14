package com.academicsystem.infrastructure_service.controller;

import com.academicsystem.infrastructure_service.dto.BuildingResponse;
import com.academicsystem.infrastructure_service.dto.CreateBuildingRequest;
import com.academicsystem.infrastructure_service.service.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @PostMapping
    public ResponseEntity<BuildingResponse> create(
            @RequestBody CreateBuildingRequest request
    ) {
        return ResponseEntity.ok(
                buildingService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<BuildingResponse>> getAll() {
        return ResponseEntity.ok(
                buildingService.getAll()
        );
    }

    @GetMapping("/{id}")
    public BuildingResponse getById(
            @PathVariable Long id
    ) {
        return buildingService.getById(id);
    }

    @PutMapping("/{id}")
    public BuildingResponse update(
            @PathVariable Long id,
            @Valid @RequestBody
            CreateBuildingRequest request
    ) {

        return buildingService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        buildingService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
