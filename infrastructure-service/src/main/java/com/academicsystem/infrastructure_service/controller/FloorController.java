package com.academicsystem.infrastructure_service.controller;

import com.academicsystem.infrastructure_service.dto.CreateFloorRequest;
import com.academicsystem.infrastructure_service.dto.FloorResponse;
import com.academicsystem.infrastructure_service.entity.Floor;
import com.academicsystem.infrastructure_service.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/floors")
@RequiredArgsConstructor
public class FloorController {

    private final FloorService floorService;

    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FloorResponse> create(
            @RequestBody CreateFloorRequest request
    ) {
        return ResponseEntity.ok(
                floorService.create(request)
        );
    }

    @GetMapping
    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<List<FloorResponse>> getAll() {

        return ResponseEntity.ok(
                floorService.getAll()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<FloorResponse> getById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                floorService.getById(id)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN')")
    public ResponseEntity<FloorResponse> update(
            @PathVariable Long id,
            @RequestBody CreateFloorRequest request
    ) {

        return ResponseEntity.ok(
                floorService.update(id, request)
        );
    }

    @GetMapping("/building/{buildingId}")
    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<List<FloorResponse>> getByBuilding(
            @PathVariable Long buildingId
    ) {
        return ResponseEntity.ok(
                floorService.getByBuilding(buildingId)
        );
    }

    @PreAuthorize("@roleSecurity.hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        floorService.delete(id);

        return ResponseEntity.noContent().build();
    }
}