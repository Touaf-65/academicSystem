package com.academicsystem.course_service.repository;

import com.academicsystem.course_service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository
        extends JpaRepository<Course, Long> {

    boolean existsByCode(String code);

    List<Course> findByDepartmentId(Long departmentId);

    List<Course> findByTeacherIdsContaining(Long teacherId);
}