package com.academicsystem.department_service.service.impl;

import com.academicsystem.department_service.dto.CreateProgramRequest;
import com.academicsystem.department_service.dto.ProgramResponse;
import com.academicsystem.department_service.entity.Program;
import com.academicsystem.department_service.repository.CycleRepository;
import com.academicsystem.department_service.repository.DepartmentRepository;
import com.academicsystem.department_service.repository.ProgramRepository;
import com.academicsystem.department_service.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final DepartmentRepository departmentRepository;
    private final CycleRepository cycleRepository;

    @Override
    public ProgramResponse create(CreateProgramRequest request) {

        if (!departmentRepository.existsById(request.departmentId())) {
            throw new RuntimeException("Department not found");
        }

        if (programRepository.existsByCode(request.code())) {
            throw new RuntimeException("Program code already exists");
        }

        Program program = Program.builder()
                .nom(request.nom())
                .code(request.code())
                .description(request.description())
                .departmentId(request.departmentId())
                .build();

        programRepository.save(program);

        return map(program);
    }

    @Override
    public List<ProgramResponse> getAll() {

        return programRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<ProgramResponse> getByDepartment(Long departmentId) {

        return programRepository.findByDepartmentId(departmentId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public ProgramResponse getById(Long id) {

        return map(findProgram(id));
    }

    @Override
    public ProgramResponse update(
            Long id,
            CreateProgramRequest request
    ) {

        Program program = findProgram(id);

        program.setNom(request.nom());
        program.setCode(request.code());
        program.setDescription(request.description());

        programRepository.save(program);

        return map(program);
    }

    @Override
    public void delete(Long id) {

        if (cycleRepository.existsByProgramId(id)) {
            throw new RuntimeException(
                    "Cannot delete program with cycles"
            );
        }

        programRepository.delete(findProgram(id));
    }

    private Program findProgram(Long id) {

        return programRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Program not found"));
    }

    private ProgramResponse map(Program program) {

        return ProgramResponse.builder()
                .id(program.getId())
                .nom(program.getNom())
                .code(program.getCode())
                .description(program.getDescription())
                .departmentId(program.getDepartmentId())
                .build();
    }
}