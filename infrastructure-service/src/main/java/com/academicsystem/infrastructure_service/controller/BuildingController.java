package com.academicsystem.infrastructure_service.controller;

import com.academicsystem.infrastructure_service.dto.BuildingResponse;
import com.academicsystem.infrastructure_service.dto.CreateBuildingRequest;
import com.academicsystem.infrastructure_service.service.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;


    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BuildingResponse> create(
            @RequestBody CreateBuildingRequest request
    ) {
        return ResponseEntity.ok(
                buildingService.create(request)
        );
    }


    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping
    public ResponseEntity<List<BuildingResponse>> getAll() {
        return ResponseEntity.ok(
                buildingService.getAll()
        );
    }

    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping("/{id}")
    public BuildingResponse getById(
            @PathVariable Long id
    ) {
        return buildingService.getById(id);
    }

    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public BuildingResponse update(
            @PathVariable Long id,
            @Valid @RequestBody
            CreateBuildingRequest request
    ) {

        return buildingService.update(id, request);
    }

    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        buildingService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
