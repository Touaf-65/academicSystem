package com.academicsystem.schooling_service.service;

import com.academicsystem.schooling_service.dto.CreateStudentRequest;
import com.academicsystem.schooling_service.dto.StudentResponse;
import com.academicsystem.schooling_service.dto.UpdateStudentRequest;

import java.util.List;

public interface StudentService {

    StudentResponse create(CreateStudentRequest request);

    StudentResponse getById(Long id);

    List<StudentResponse> getAll();

    List<StudentResponse> getByDepartment(Long departmentId);

    List<StudentResponse> getByProgram(Long programId);

    List<StudentResponse> getByCycle(Long cycleId);

    List<StudentResponse> getByLevel(Long levelId);

    List<StudentResponse> getByClass(Long classId);

    StudentResponse update(Long id, UpdateStudentRequest request);

    void delete(Long id);
}