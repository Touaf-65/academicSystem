package com.academicsystem.infrastructure_service.repository;

import com.academicsystem.infrastructure_service.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository
        extends JpaRepository<Room, Long> {

    List<Room> findByFloorId(Long floorId);

    @Query("""
            SELECT r
            FROM Room r
            WHERE r.floor.building.id = :buildingId
       """)
    List<Room> findByBuildingId(Long buildingId);
}