package com.academicsystem.department_service.service.impl;

import com.academicsystem.department_service.dto.CreateAcademicClassRequest;
import com.academicsystem.department_service.dto.AcademicClassResponse;
import com.academicsystem.department_service.entity.AcademicClass;
import com.academicsystem.department_service.repository.AcademicClassRepository;
import com.academicsystem.department_service.repository.AcademicLevelRepository;
import com.academicsystem.department_service.service.AcademicClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicClassServiceImpl
        implements AcademicClassService {

    private final AcademicClassRepository classRepository;
    private final AcademicLevelRepository levelRepository;

    @Override
    public AcademicClassResponse create(
            CreateAcademicClassRequest request
    ) {

        if (!levelRepository.existsById(request.levelId())) {
            throw new RuntimeException("Level not found");
        }

        if (classRepository.existsByCode(request.code())) {
            throw new RuntimeException(
                    "Class code already exists"
            );
        }

        AcademicClass academicClass =
                AcademicClass.builder()
                        .nom(request.nom())
                        .code(request.code())
                        .capacite(request.capacite())
                        .levelId(request.levelId())
                        .build();

        classRepository.save(academicClass);

        return map(academicClass);
    }

    @Override
    public List<AcademicClassResponse> getAll() {

        return classRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<AcademicClassResponse> getByLevel(Long levelId) {

        return classRepository.findByLevelId(levelId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public AcademicClassResponse getById(Long id) {

        return map(findClass(id));
    }

    @Override
    public AcademicClassResponse update(
            Long id,
            CreateAcademicClassRequest request
    ) {

        AcademicClass academicClass = findClass(id);

        academicClass.setNom(request.nom());
        academicClass.setCode(request.code());
        academicClass.setCapacite(request.capacite());

        classRepository.save(academicClass);

        return map(academicClass);
    }

    @Override
    public void delete(Long id) {

        classRepository.delete(findClass(id));
    }

    private AcademicClass findClass(Long id) {

        return classRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Class not found"));
    }

    private AcademicClassResponse map(
            AcademicClass academicClass
    ) {

        return AcademicClassResponse.builder()
                .id(academicClass.getId())
                .nom(academicClass.getNom())
                .code(academicClass.getCode())
                .capacite(academicClass.getCapacite())
                .levelId(academicClass.getLevelId())
                .build();
    }
}