package com.academicsystem.department_service.repository;

import com.academicsystem.department_service.entity.AcademicClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicClassRepository
        extends JpaRepository<AcademicClass, Long> {

    List<AcademicClass> findByLevelId(Long levelId);

    boolean existsByLevelId(Long levelId);

    boolean existsByCode(String code);
}