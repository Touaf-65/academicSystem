package com.academicsystem.schooling_service.repository;

import com.academicsystem.schooling_service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    long count();

    Optional<Student> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByMatricule(String matricule);
}