package com.academicsystem.infrastructure_service.controller;

import com.academicsystem.infrastructure_service.dto.BuildingResponse;
import com.academicsystem.infrastructure_service.dto.CreateBuildingRequest;
import com.academicsystem.infrastructure_service.service.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/buildings")
@RequiredArgsConstructor
public class InternalBuildingController {

    private final BuildingService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BuildingResponse create(
            @Valid @RequestBody
            CreateBuildingRequest request
    ) {

        return service.create(request);
    }

    @GetMapping("/{id}")
    public BuildingResponse getById(
            @PathVariable Long id
    ) {

        return service.getById(id);
    }

    @GetMapping
    public List<BuildingResponse> getAll() {

        return service.getAll();
    }

//    @PutMapping("/{id}")
//    public BuildingResponse update(
//            @PathVariable Long id,
//            @Valid @RequestBody
//            CreateBuildingRequest request
//    ) {
//
//        return service.update(id, request);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ) {

        service.delete(id);
    }
}