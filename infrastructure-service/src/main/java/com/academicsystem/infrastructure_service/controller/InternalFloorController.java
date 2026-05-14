package com.academicsystem.infrastructure_service.controller;

import com.academicsystem.infrastructure_service.dto.CreateFloorRequest;
import com.academicsystem.infrastructure_service.dto.FloorResponse;
import com.academicsystem.infrastructure_service.service.FloorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/floors")
@RequiredArgsConstructor
public class InternalFloorController {

    private final FloorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FloorResponse create(
            @Valid @RequestBody
            CreateFloorRequest request
    ) {

        return service.create(request);
    }

//    @GetMapping("/{id}")
//    public FloorResponse getById(
//            @PathVariable Long id
//    ) {
//
//        return service.findById(id);
//    }

//    @GetMapping
//    public List<FloorResponse> getAll() {
//
//        return service.findAll();
//    }

//    @PutMapping("/{id}")
//    public FloorResponse update(
//            @PathVariable Long id,
//            @Valid @RequestBody
//            CreateFloorRequest request
//    ) {
//
//        return service.update(id, request);
//    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(
//            @PathVariable Long id
//    ) {
//
//        service.delete(id);
//    }
}