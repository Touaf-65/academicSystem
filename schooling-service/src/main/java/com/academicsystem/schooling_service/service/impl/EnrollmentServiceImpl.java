package com.academicsystem.schooling_service.service.impl;

import com.academicsystem.schooling_service.client.DepartmentClient;
import com.academicsystem.schooling_service.dto.*;
import com.academicsystem.schooling_service.entity.Enrollment;
import com.academicsystem.schooling_service.entity.Student;
import com.academicsystem.schooling_service.enums.EnrollmentStatus;
import com.academicsystem.schooling_service.repository.EnrollmentRepository;
import com.academicsystem.schooling_service.repository.StudentRepository;
import com.academicsystem.schooling_service.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl
        implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final StudentRepository studentRepository;

    private final DepartmentClient departmentClient;

    @Override
    public EnrollmentResponse create(
            CreateEnrollmentRequest request
    ) {

        studentRepository.findById(request.studentId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        departmentClient.getClassById(
                request.classRoomId()
        );

        boolean alreadyExists =
                enrollmentRepository
                        .findByStudentIdAndClassRoomIdAndAcademicYear(
                                request.studentId(),
                                request.classRoomId(),
                                request.academicYear()
                        )
                        .isPresent();

        if (alreadyExists) {

            throw new RuntimeException(
                    "Student already enrolled"
            );
        }

        Enrollment enrollment =
                Enrollment.builder()
                        .studentId(request.studentId())
                        .classRoomId(request.classRoomId())
                        .academicYear(request.academicYear())
                        .status(EnrollmentStatus.ACTIVE)
                        .build();

        return map(
                enrollmentRepository.save(enrollment)
        );
    }

    @Override
    public EnrollmentResponse getById(Long id) {

        return map(
                enrollmentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Enrollment not found"
                                ))
        );
    }

    @Override
    public List<EnrollmentResponse> getAll() {

        return enrollmentRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<EnrollmentResponse> getByStudent(
            Long studentId
    ) {

        return enrollmentRepository
                .findByStudentId(studentId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<EnrollmentResponse> getByClass(
            Long classId
    ) {

        return enrollmentRepository
                .findByClassRoomId(classId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<EnrollmentResponse> getByLevel(
            Long levelId
    ) {

        return enrollmentRepository.findAll()
                .stream()
                .filter(enrollment -> {

                    AcademicClassResponse academicClass =
                            departmentClient.getClassById(
                                    enrollment.getClassRoomId()
                            );

                    return academicClass.levelId()
                            .equals(levelId);
                })
                .map(this::map)
                .toList();
    }

    @Override
    public List<EnrollmentResponse> getByCycle(
            Long cycleId
    ) {

        return enrollmentRepository.findAll()
                .stream()
                .filter(enrollment -> {

                    AcademicClassResponse academicClass =
                            departmentClient.getClassById(
                                    enrollment.getClassRoomId()
                            );

                    LevelResponse level =
                            departmentClient.getLevelById(
                                    academicClass.levelId()
                            );

                    return level.cycleId()
                            .equals(cycleId);
                })
                .map(this::map)
                .toList();
    }

    @Override
    public List<EnrollmentResponse> getByProgram(
            Long programId
    ) {

        return enrollmentRepository.findAll()
                .stream()
                .filter(enrollment -> {

                    AcademicClassResponse academicClass =
                            departmentClient.getClassById(
                                    enrollment.getClassRoomId()
                            );

                    LevelResponse level =
                            departmentClient.getLevelById(
                                    academicClass.levelId()
                            );

                    CycleResponse cycle =
                            departmentClient.getCycleById(
                                    level.cycleId()
                            );

                    return cycle.programId()
                            .equals(programId);
                })
                .map(this::map)
                .toList();
    }

    @Override
    public List<EnrollmentResponse> getByDepartment(
            Long departmentId
    ) {

        return enrollmentRepository.findAll()
                .stream()
                .filter(enrollment -> {

                    AcademicClassResponse academicClass =
                            departmentClient.getClassById(
                                    enrollment.getClassRoomId()
                            );

                    LevelResponse level =
                            departmentClient.getLevelById(
                                    academicClass.levelId()
                            );

                    CycleResponse cycle =
                            departmentClient.getCycleById(
                                    level.cycleId()
                            );

                    ProgramResponse program =
                            departmentClient.getProgramById(
                                    cycle.programId()
                            );

                    return program.departmentId()
                            .equals(departmentId);
                })
                .map(this::map)
                .toList();
    }

    @Override
    public EnrollmentResponse updateStatus(
            Long id,
            EnrollmentStatus status
    ) {

        Enrollment enrollment =
                enrollmentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Enrollment not found"
                                ));

        enrollment.setStatus(status);

        return map(
                enrollmentRepository.save(enrollment)
        );
    }

    @Override
    public void delete(Long id) {

        enrollmentRepository.deleteById(id);
    }

    private EnrollmentResponse map(
            Enrollment enrollment
    ) {

        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getStudentId(),
                enrollment.getClassRoomId(),
                enrollment.getAcademicYear(),
                enrollment.getStatus()
        );
    }
}