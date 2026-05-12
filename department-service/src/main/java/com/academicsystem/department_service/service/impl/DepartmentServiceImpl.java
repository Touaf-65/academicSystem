package com.academicsystem.department_service.service.impl;

import com.academicsystem.department_service.dto.CreateDepartmentRequest;
import com.academicsystem.department_service.dto.DepartmentResponse;
import com.academicsystem.department_service.entity.Department;
import com.academicsystem.department_service.repository.DepartmentRepository;
import com.academicsystem.department_service.service.DepartmentService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl
        implements DepartmentService {

    private final DepartmentRepository repository;

    @Override
    public DepartmentResponse create(
            CreateDepartmentRequest request
    ) {

        Department department = Department.builder()
                .nom(request.nom())
                .description(request.description())
                .build();

        Department saved = repository.save(department);

        return map(saved);
    }

    @Override
    public DepartmentResponse getById(Long id) {

        Department department = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found")
                );

        return map(department);
    }

    @Override
    public List<DepartmentResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public DepartmentResponse update(
            Long id,
            CreateDepartmentRequest request
    ) {

        Department department = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found")
                );

        department.setNom(request.nom());
        department.setDescription(request.description());

        Department updated = repository.save(department);

        return map(updated);
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);
    }

    private DepartmentResponse map(
            Department department
    ) {

        return new DepartmentResponse(
                department.getId(),
                department.getNom(),
                department.getDescription()
        );
    }
}