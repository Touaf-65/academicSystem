package com.academicsystem.infrastructure_service.service;

import com.academicsystem.infrastructure_service.dto.CreateRoomRequest;
import com.academicsystem.infrastructure_service.dto.RoomResponse;
import com.academicsystem.infrastructure_service.entity.Room;

import java.util.List;

public interface RoomService {

    RoomResponse create(CreateRoomRequest request);

    RoomResponse getById(Long id);

    RoomResponse update(
            Long id,
            CreateRoomRequest request
    );

    void delete(Long id);

    List<RoomResponse> getAll();

    List<RoomResponse> getByFloor(Long floorId);

    List<RoomResponse> getByBuilding(Long buildingId);
}
