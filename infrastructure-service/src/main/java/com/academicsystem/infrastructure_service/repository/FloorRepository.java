package com.academicsystem.infrastructure_service.repository;

import com.academicsystem.infrastructure_service.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository
        extends JpaRepository<Floor, Long> {

    List<Floor> findByBuildingId(Long buildingId);
}