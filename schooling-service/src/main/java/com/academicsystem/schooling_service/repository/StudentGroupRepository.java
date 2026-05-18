package com.academicsystem.schooling_service.repository;

import com.academicsystem.schooling_service.entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentGroupRepository
        extends JpaRepository<StudentGroup, Long> {

    List<StudentGroup> findByClassRoomId(Long classRoomId);
}