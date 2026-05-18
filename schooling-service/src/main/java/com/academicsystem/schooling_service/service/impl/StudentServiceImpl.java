package com.academicsystem.schooling_service.service.impl;

import com.academicsystem.schooling_service.client.AuthClient;
import com.academicsystem.schooling_service.client.DepartmentClient;
import com.academicsystem.schooling_service.dto.*;
import com.academicsystem.schooling_service.dto.auth.CreateAccountRequest;
import com.academicsystem.schooling_service.dto.auth.CreateAccountResponse;
import com.academicsystem.schooling_service.entity.Enrollment;
import com.academicsystem.schooling_service.entity.Student;
import com.academicsystem.schooling_service.repository.EnrollmentRepository;
import com.academicsystem.schooling_service.repository.StudentRepository;
import com.academicsystem.schooling_service.service.MatriculeGeneratorService;
import com.academicsystem.schooling_service.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl
        implements StudentService {

    private final StudentRepository studentRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final DepartmentClient departmentClient;

    private final AuthClient authClient;

    private final MatriculeGeneratorService matriculeGeneratorService;

    @Override
    public StudentResponse create(
            CreateStudentRequest request
    ) {

        if (studentRepository.existsByEmail(
                request.email()
        )) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }

        AcademicClassResponse academicClass =
                departmentClient.getClassById(
                        request.classId()
                );

        LevelResponse level = departmentClient.getLevelById(
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

        String matricule =
                matriculeGeneratorService.generate(
                        request.academicYear(),
                        program.nom()
                );

        CreateAccountResponse authResponse =
                authClient.createAccount(

                        new CreateAccountRequest(
                                request.email(),
                                request.password(),
                                "STUDENT"
                        )
                );


        Student student = Student.builder()
                .nom(request.lastName() + request.firstName())
                .email(request.email())
                .matricule(matricule)
                .authUserId(authResponse.id())
                .build();

        Student saved =
                studentRepository.save(student);

        return map(saved);
    }

    @Override
    public StudentResponse getById(Long id) {

        Student student =
                studentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student not found"
                                ));

        return map(student);
    }

    @Override
    public List<StudentResponse> getAll() {

        return studentRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<StudentResponse> getByClass(Long classId) {

        List<Enrollment> enrollments =
                enrollmentRepository.findByClassRoomId(classId);

        return enrollments.stream()
                .map(enrollment ->
                        studentRepository.findById(
                                enrollment.getStudentId()
                        ).orElse(null)
                )
                .filter(Objects::nonNull)
                .map(this::map)
                .toList();
    }

    @Override
    public List<StudentResponse> getByLevel(Long levelId) {

        return studentRepository.findAll()
                .stream()
                .filter(student -> {

                    List<Enrollment> enrollments =
                            enrollmentRepository.findByStudentId(
                                    student.getId()
                            );

                    return enrollments.stream()
                            .anyMatch(enrollment -> {

                                AcademicClassResponse academicClass =
                                        departmentClient.getClassById(
                                                enrollment.getClassRoomId()
                                        );

                                return academicClass.levelId()
                                        .equals(levelId);
                            });
                })
                .map(this::map)
                .toList();
    }

    @Override
    public List<StudentResponse> getByCycle(Long cycleId) {

        return studentRepository.findAll()
                .stream()
                .filter(student -> {

                    List<Enrollment> enrollments =
                            enrollmentRepository.findByStudentId(
                                    student.getId()
                            );

                    return enrollments.stream()
                            .anyMatch(enrollment -> {

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
                            });
                })
                .map(this::map)
                .toList();
    }

    @Override
    public List<StudentResponse> getByProgram(Long programId) {

        return studentRepository.findAll()
                .stream()
                .filter(student -> {

                    List<Enrollment> enrollments =
                            enrollmentRepository.findByStudentId(
                                    student.getId()
                            );

                    return enrollments.stream()
                            .anyMatch(enrollment -> {

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
                            });
                })
                .map(this::map)
                .toList();
    }

    @Override
    public List<StudentResponse> getByDepartment(Long departmentId) {

        return studentRepository.findAll()
                .stream()
                .filter(student -> {

                    List<Enrollment> enrollments =
                            enrollmentRepository.findByStudentId(
                                    student.getId()
                            );

                    return enrollments.stream()
                            .anyMatch(enrollment -> {

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
                            });
                })
                .map(this::map)
                .toList();
    }

    @Override
    public StudentResponse update(
            Long id,
            UpdateStudentRequest request
    ) {

        Student student =
                studentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student not found"
                                ));

        student.setNom(request.nom());
        student.setEmail(request.email());

        return map(
                studentRepository.save(student)
        );
    }

    @Override
    public void delete(Long id) {

        studentRepository.deleteById(id);
    }

    private StudentResponse map(
            Student student
    ) {

        return new StudentResponse(
                student.getId(),
                student.getNom(),
                student.getEmail(),
                student.getMatricule(),
                student.getAuthUserId()
        );
    }
}