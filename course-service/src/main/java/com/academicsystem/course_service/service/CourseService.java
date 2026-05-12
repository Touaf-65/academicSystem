package com.academicsystem.course_service.service;

import com.academicsystem.course_service.dto.CourseResponse;
import com.academicsystem.course_service.dto.CreateCourseRequest;

import java.util.List;

public interface CourseService {

    CourseResponse create(CreateCourseRequest request);

    CourseResponse findById(Long id);

    List<CourseResponse> findAll();

    List<CourseResponse> findByDepartment(Long departmentId);

    List<CourseResponse> findByTeacher(Long teacherId);

    CourseResponse update(Long id, CreateCourseRequest request);

    CourseResponse assignTeacher(
            Long courseId,
            Long teacherId
    );

    CourseResponse removeTeacher(
            Long courseId,
            Long teacherId
    );

    void delete(Long id);
}