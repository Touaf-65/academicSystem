package com.academicsystem.infrastructure_service.service.impl;

import com.academicsystem.infrastructure_service.dto.CreateRoomRequest;
import com.academicsystem.infrastructure_service.dto.RoomResponse;
import com.academicsystem.infrastructure_service.entity.Floor;
import com.academicsystem.infrastructure_service.entity.Room;
import com.academicsystem.infrastructure_service.repository.FloorRepository;
import com.academicsystem.infrastructure_service.repository.RoomRepository;
import com.academicsystem.infrastructure_service.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl
        implements RoomService {

    private final RoomRepository roomRepository;

    private final FloorRepository floorRepository;

    @Override
    public RoomResponse create(
            CreateRoomRequest request
    ) {

        Floor floor =
                floorRepository.findById(
                                request.floorId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Floor not found"
                                ));

        int usedCapacity = floor.getRooms()
                .stream()
                .mapToInt(Room::getCapacite)
                .sum();

        if (
                usedCapacity + request.capacite()
                        > floor.getCapacite()
        ) {

            throw new RuntimeException(
                    "Floor capacity exceeded"
            );
        }

        Room room = Room.builder()
                .nom(request.nom())
                .capacite(request.capacite())
                .type(request.type())
                .floor(floor)
                .build();

        Room saved =
                roomRepository.save(room);

        return mapToResponse(saved);
    }

    @Override
    public RoomResponse getById(Long id) {

        Room room =
                roomRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Room not found"
                                ));

        return mapToResponse(room);
    }

    @Override
    public RoomResponse update(
            Long id,
            CreateRoomRequest request
    ) {

        Room room =
                roomRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Room not found"
                                ));

        Floor floor =
                floorRepository.findById(
                                request.floorId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Floor not found"
                                ));

        int usedCapacity = floor.getRooms()
                .stream()
                .filter(r -> !r.getId().equals(id))
                .mapToInt(Room::getCapacite)
                .sum();

        if (
                usedCapacity + request.capacite()
                        > floor.getCapacite()
        ) {

            throw new RuntimeException(
                    "Floor capacity exceeded"
            );
        }

        room.setNom(request.nom());
        room.setCapacite(request.capacite());
        room.setType(
                request.type()
        );
        room.setFloor(floor);

        Room updated =
                roomRepository.save(room);

        return mapToResponse(updated);
    }

    @Override
    public List<RoomResponse> getAll() {

        return roomRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<RoomResponse> getByFloor(
            Long floorId
    ) {

        return roomRepository.findByFloorId(
                        floorId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<RoomResponse> getByBuilding(
            Long buildingId
    ) {

        return roomRepository.findByBuildingId(
                        buildingId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private RoomResponse mapToResponse(
            Room room
    ) {

        return RoomResponse.builder()
                .id(room.getId())
                .nom(room.getNom())
                .capacite(room.getCapacite())
                .type(room.getType())
                .floorId(
                        room.getFloor().getId()
                )
                .build();
    }

    @Override
    public void delete(Long id) {

        Room room =
                roomRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Room not found"
                                ));

        roomRepository.delete(room);
    }

    private RoomResponse map(
            Room room
    ) {

        return new RoomResponse(
                room.getId(),
                room.getNom(),
                room.getCapacite(),
                room.getType(),
                room.getFloor().getId()
        );
    }
}