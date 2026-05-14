package com.academicsystem.infrastructure_service.controller;

import com.academicsystem.infrastructure_service.dto.CreateRoomRequest;
import com.academicsystem.infrastructure_service.dto.RoomResponse;
import com.academicsystem.infrastructure_service.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> create(
            @RequestBody CreateRoomRequest request
    ) {
        return ResponseEntity.ok(
                roomService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAll() {

        return ResponseEntity.ok(
                roomService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                roomService.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(
            @PathVariable Long id,
            @RequestBody CreateRoomRequest request
    ) {

        return ResponseEntity.ok(
                roomService.update(id, request)
        );
    }

    @GetMapping("/floor/{floorId}")
    public ResponseEntity<List<RoomResponse>> getByFloor(
            @PathVariable Long floorId
    ) {
        return ResponseEntity.ok(
                roomService.getByFloor(floorId)
        );
    }

    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<RoomResponse>>
    getByBuilding(
            @PathVariable Long buildingId
    ) {

        return ResponseEntity.ok(
                roomService.getByBuilding(buildingId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        roomService.delete(id);

        return ResponseEntity.noContent().build();
    }
}