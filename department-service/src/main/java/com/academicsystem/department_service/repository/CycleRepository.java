package com.academicsystem.department_service.repository;

import com.academicsystem.department_service.entity.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CycleRepository
        extends JpaRepository<Cycle, Long> {

    List<Cycle> findByProgramId(Long programId);

    boolean existsByProgramId(Long programId);
}