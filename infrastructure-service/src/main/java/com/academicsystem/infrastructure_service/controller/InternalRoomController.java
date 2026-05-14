package com.academicsystem.infrastructure_service.controller;

import com.academicsystem.infrastructure_service.dto.CreateRoomRequest;
import com.academicsystem.infrastructure_service.dto.RoomResponse;
import com.academicsystem.infrastructure_service.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/rooms")
@RequiredArgsConstructor
public class InternalRoomController {

    private final RoomService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomResponse create(
            @Valid @RequestBody
            CreateRoomRequest request
    ) {

        return service.create(request);
    }

//    @GetMapping("/{id}")
//    public RoomResponse getById(
//            @PathVariable Long id
//    ) {
//
//        return service.findById(id);
//    }

    @GetMapping
    public List<RoomResponse> getAll() {

        return service.getAll();
    }

//    @PutMapping("/{id}")
//    public RoomResponse update(
//            @PathVariable Long id,
//            @Valid @RequestBody
//            CreateRoomRequest request
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