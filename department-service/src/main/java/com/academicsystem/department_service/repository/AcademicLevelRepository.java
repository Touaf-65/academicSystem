package com.academicsystem.department_service.repository;

import com.academicsystem.department_service.entity.AcademicLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicLevelRepository
        extends JpaRepository<AcademicLevel, Long> {

    List<AcademicLevel> findByCycleId(Long cycleId);

    boolean existsByCycleId(Long cycleId);

    boolean existsByCycleIdAndOrdre(
            Long cycleId,
            Integer ordre
    );
}