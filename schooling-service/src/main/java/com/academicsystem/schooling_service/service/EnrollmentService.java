package com.academicsystem.schooling_service.service;

import com.academicsystem.schooling_service.dto.CreateEnrollmentRequest;
import com.academicsystem.schooling_service.dto.EnrollmentResponse;
import com.academicsystem.schooling_service.enums.EnrollmentStatus;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponse create(CreateEnrollmentRequest request);

    EnrollmentResponse getById(Long id);

    List<EnrollmentResponse> getAll();

    List<EnrollmentResponse> getByStudent(Long studentId);

    List<EnrollmentResponse> getByClass(Long classId);

    List<EnrollmentResponse> getByProgram(Long programId);

    List<EnrollmentResponse> getByCycle(Long cycleId);

    List<EnrollmentResponse> getByLevel(Long levelId);

    List<EnrollmentResponse> getByDepartment(Long departmentId);

    EnrollmentResponse updateStatus(
            Long id,
            EnrollmentStatus status
    );

    void delete(Long id);
}