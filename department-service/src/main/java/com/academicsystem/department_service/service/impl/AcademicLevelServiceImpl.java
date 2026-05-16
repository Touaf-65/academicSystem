package com.academicsystem.department_service.service.impl;

import com.academicsystem.department_service.dto.CreateAcademicLevelRequest;
import com.academicsystem.department_service.dto.AcademicLevelResponse;
import com.academicsystem.department_service.entity.AcademicLevel;
import com.academicsystem.department_service.entity.Cycle;
import com.academicsystem.department_service.repository.AcademicClassRepository;
import com.academicsystem.department_service.repository.AcademicLevelRepository;
import com.academicsystem.department_service.repository.CycleRepository;
import com.academicsystem.department_service.service.AcademicLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicLevelServiceImpl
        implements AcademicLevelService {

    private final AcademicLevelRepository levelRepository;
    private final CycleRepository cycleRepository;
    private final AcademicClassRepository classRepository;

    @Override
    public AcademicLevelResponse create(
            CreateAcademicLevelRequest request
    ) {

        Cycle cycle = cycleRepository.findById(request.cycleId())
                .orElseThrow(() ->
                        new RuntimeException("Cycle not found"));

        if (request.ordre() > cycle.getDureeAnnees()) {
            throw new RuntimeException(
                    "Level exceeds cycle duration"
            );
        }

        if (levelRepository.existsByCycleIdAndOrdre(
                request.cycleId(),
                request.ordre()
        )) {
            throw new RuntimeException(
                    "Level order already exists"
            );
        }

        AcademicLevel level = AcademicLevel.builder()
                .nom(request.nom())
                .code(request.code())
                .ordre(request.ordre())
                .cycleId(request.cycleId())
                .build();

        levelRepository.save(level);

        return map(level);
    }

    @Override
    public List<AcademicLevelResponse> getAll() {

        return levelRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<AcademicLevelResponse> getByCycle(Long cycleId) {

        return levelRepository.findByCycleId(cycleId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public AcademicLevelResponse getById(Long id) {

        return map(findLevel(id));
    }

    @Override
    public AcademicLevelResponse update(
            Long id,
            CreateAcademicLevelRequest request
    ) {

        AcademicLevel level = findLevel(id);

        level.setNom(request.nom());
        level.setCode(request.code());

        levelRepository.save(level);

        return map(level);
    }

    @Override
    public void delete(Long id) {

        if (classRepository.existsByLevelId(id)) {
            throw new RuntimeException(
                    "Cannot delete level with classes"
            );
        }

        levelRepository.delete(findLevel(id));
    }

    private AcademicLevel findLevel(Long id) {

        return levelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Level not found"));
    }

    private AcademicLevelResponse map(AcademicLevel level) {

        return AcademicLevelResponse.builder()
                .id(level.getId())
                .nom(level.getNom())
                .code(level.getCode())
                .ordre(level.getOrdre())
                .cycleId(level.getCycleId())
                .build();
    }
}