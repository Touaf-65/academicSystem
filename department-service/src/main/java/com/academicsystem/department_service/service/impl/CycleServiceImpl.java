package com.academicsystem.department_service.service.impl;

import com.academicsystem.department_service.dto.CreateCycleRequest;
import com.academicsystem.department_service.dto.CycleResponse;
import com.academicsystem.department_service.entity.Cycle;
import com.academicsystem.department_service.repository.AcademicLevelRepository;
import com.academicsystem.department_service.repository.CycleRepository;
import com.academicsystem.department_service.repository.ProgramRepository;
import com.academicsystem.department_service.service.CycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CycleServiceImpl implements CycleService {

    private final CycleRepository cycleRepository;
    private final ProgramRepository programRepository;
    private final AcademicLevelRepository levelRepository;

    @Override
    public CycleResponse create(CreateCycleRequest request) {

        if (!programRepository.existsById(request.programId())) {
            throw new RuntimeException("Program not found");
        }

        Cycle cycle = Cycle.builder()
                .nom(request.nom())
                .code(request.code())
                .dureeAnnees(request.dureeAnnees())
                .programId(request.programId())
                .build();

        cycleRepository.save(cycle);

        return map(cycle);
    }

    @Override
    public List<CycleResponse> getAll() {

        return cycleRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<CycleResponse> getByProgram(Long programId) {

        return cycleRepository.findByProgramId(programId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public CycleResponse getById(Long id) {

        return map(findCycle(id));
    }

    @Override
    public CycleResponse update(
            Long id,
            CreateCycleRequest request
    ) {

        Cycle cycle = findCycle(id);

        cycle.setNom(request.nom());
        cycle.setCode(request.code());
        cycle.setDureeAnnees(request.dureeAnnees());

        cycleRepository.save(cycle);

        return map(cycle);
    }

    @Override
    public void delete(Long id) {

        if (levelRepository.existsByCycleId(id)) {
            throw new RuntimeException(
                    "Cannot delete cycle with levels"
            );
        }

        cycleRepository.delete(findCycle(id));
    }

    private Cycle findCycle(Long id) {

        return cycleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cycle not found"));
    }

    private CycleResponse map(Cycle cycle) {

        return CycleResponse.builder()
                .id(cycle.getId())
                .nom(cycle.getNom())
                .code(cycle.getCode())
                .dureeAnnees(cycle.getDureeAnnees())
                .programId(cycle.getProgramId())
                .build();
    }
}