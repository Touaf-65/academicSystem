package com.academicsystem.department_service.service.impl;

import com.academicsystem.department_service.dto.CreateDepartmentRequest;
import com.academicsystem.department_service.dto.DepartmentResponse;
import com.academicsystem.department_service.entity.Department;
import com.academicsystem.department_service.repository.DepartmentRepository;
import com.academicsystem.department_service.repository.ProgramRepository;
import com.academicsystem.department_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ProgramRepository programRepository;

    @Override
    public DepartmentResponse create(CreateDepartmentRequest request) {

        if (departmentRepository.existsByNom(request.nom())) {
            throw new RuntimeException("Department already exists");
        }

        Department department = Department.builder()
                .nom(request.nom())
                .description(request.description())
                .build();

        departmentRepository.save(department);

        return map(department);
    }

    @Override
    public List<DepartmentResponse> getAll() {

        return departmentRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public DepartmentResponse getById(Long id) {

        return map(findDepartment(id));
    }

    @Override
    public DepartmentResponse update(
            Long id,
            CreateDepartmentRequest request
    ) {

        Department department = findDepartment(id);

        department.setNom(request.nom());
        department.setDescription(request.description());

        departmentRepository.save(department);

        return map(department);
    }

    @Override
    public void delete(Long id) {

        if (programRepository.existsByDepartmentId(id)) {
            throw new RuntimeException(
                    "Cannot delete department with programs"
            );
        }

        departmentRepository.delete(findDepartment(id));
    }

    private Department findDepartment(Long id) {

        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));
    }

    private DepartmentResponse map(Department department) {

        return DepartmentResponse.builder()
                .id(department.getId())
                .nom(department.getNom())
                .description(department.getDescription())
                .build();
    }
}