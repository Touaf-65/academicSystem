package com.academicsystem.teacher_service.service.impl;

import com.academicsystem.teacher_service.client.AuthClient;
import com.academicsystem.teacher_service.client.DepartmentClient;
import com.academicsystem.teacher_service.dto.CreateTeacherRequest;
import com.academicsystem.teacher_service.dto.TeacherResponse;
import com.academicsystem.teacher_service.dto.auth.CreateAccountRequest;
import com.academicsystem.teacher_service.dto.auth.CreateAccountResponse;
import com.academicsystem.teacher_service.entity.Teacher;
import com.academicsystem.teacher_service.repository.TeacherRepository;
import com.academicsystem.teacher_service.service.TeacherService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl
        implements TeacherService {

    private final TeacherRepository repository;

    private final DepartmentClient departmentClient;

    private final AuthClient authClient;


    @Override
    public TeacherResponse create(
            CreateTeacherRequest request
    ) {

        /*
         * Validation départements
         */
        if (request.departmentIds() != null) {

            request.departmentIds()
                    .forEach(
                            departmentClient::getDepartmentById
                    );
        }

        CreateAccountResponse authResponse =
                authClient.createAccount(

                        new CreateAccountRequest(
                                request.email(),
                                request.password(),
                                "TEACHER"
                        )
                );

        Teacher teacher = Teacher.builder()
                .nom(request.nom())
                .email(request.email())
                .departmentIds(request.departmentIds())
                .authUserId(authResponse.id())
                .build();

        Teacher saved = repository.save(teacher);

        return map(saved);
    }

    @Override
    public TeacherResponse getById(Long id) {

        Teacher teacher = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Teacher not found"
                        )
                );

        return map(teacher);
    }

    @Override
    public List<TeacherResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public TeacherResponse update(
            Long id,
            CreateTeacherRequest request
    ) {

        Teacher teacher = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Teacher not found"
                        )
                );

        teacher.setNom(request.nom());

        teacher.setEmail(request.email());

        teacher.setDepartmentIds(
                request.departmentIds()
        );

        Teacher updated = repository.save(teacher);

        return map(updated);
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);
    }

    private TeacherResponse map(
            Teacher teacher
    ) {

        return new TeacherResponse(
                teacher.getId(),
                teacher.getNom(),
                teacher.getEmail(),
                teacher.getDepartmentIds(),
                teacher.getCourseIds()
        );
    }
}