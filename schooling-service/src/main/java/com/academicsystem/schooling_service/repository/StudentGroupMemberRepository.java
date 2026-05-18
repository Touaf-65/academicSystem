package com.academicsystem.schooling_service.repository;

import com.academicsystem.schooling_service.entity.StudentGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentGroupMemberRepository
        extends JpaRepository<StudentGroupMember, Long> {

    List<StudentGroupMember> findByGroupId(Long groupId);

    List<StudentGroupMember> findByStudentId(Long studentId);

    Optional<StudentGroupMember> findByGroupIdAndStudentId(
            Long groupId,
            Long studentId
    );

    long countByGroupId(Long groupId);
}