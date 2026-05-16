package com.academicsystem.department_service.repository;

import com.academicsystem.department_service.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository
        extends JpaRepository<Program, Long> {

    List<Program> findByDepartmentId(Long departmentId);

    boolean existsByDepartmentId(Long departmentId);

    boolean existsByCode(String code);
}