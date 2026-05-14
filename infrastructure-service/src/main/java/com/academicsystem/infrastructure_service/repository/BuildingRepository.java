package com.academicsystem.infrastructure_service.repository;

import com.academicsystem.infrastructure_service.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository
        extends JpaRepository<Building, Long> {
}