package com.academicsystem.schooling_service.repository;

import com.academicsystem.schooling_service.entity.Enrollment;
import com.academicsystem.schooling_service.enums.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findByClassRoomId(Long classId);

    List<Enrollment> findByStatus(
            EnrollmentStatus status
    );

    Optional<Enrollment>
    findByStudentIdAndClassRoomIdAndAcademicYear(
            Long studentId,
            Long classRoomId,
            String academicYear
    );
}