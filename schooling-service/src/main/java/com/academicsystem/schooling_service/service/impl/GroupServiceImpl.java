package com.academicsystem.schooling_service.service.impl;

import com.academicsystem.schooling_service.client.DepartmentClient;
import com.academicsystem.schooling_service.dto.*;
import com.academicsystem.schooling_service.entity.*;
import com.academicsystem.schooling_service.repository.*;
import com.academicsystem.schooling_service.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl
        implements GroupService {

    private final StudentGroupRepository groupRepository;

    private final StudentRepository studentRepository;

    private final StudentGroupMemberRepository memberRepository;

    private final DepartmentClient departmentClient;

    @Override
    public GroupResponse create(
            CreateGroupRequest request
    ) {

        departmentClient.getClassById(
                request.classRoomId()
        );

        StudentGroup group =
                StudentGroup.builder()
                        .nom(request.nom())
                        .type(request.type())
                        .capacite(request.capaciteMax())
                        .classRoomId(request.classRoomId())
                        .build();

        return map(groupRepository.save(group));
    }

    @Override
    public GroupResponse getById(Long id) {

        return map(
                groupRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Group not found"
                                ))
        );
    }

    @Override
    public List<GroupResponse> getAll() {

        return groupRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<GroupResponse> getByClass(
            Long classId
    ) {

        return groupRepository.findByClassRoomId(classId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<GroupResponse> getByLevel(
            Long levelId
    ) {

        return groupRepository.findAll()
                .stream()
                .filter(group -> {

                    AcademicClassResponse academicClass =
                            departmentClient.getClassById(
                                    group.getClassRoomId()
                            );

                    return academicClass.levelId()
                            .equals(levelId);
                })
                .map(this::map)
                .toList();
    }

    @Override
    public List<GroupResponse> getByDepartment(
            Long departmentId
    ) {

        return groupRepository.findAll()
                .stream()
                .filter(group -> {

                    AcademicClassResponse academicClass =
                            departmentClient.getClassById(
                                    group.getClassRoomId()
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
    public GroupResponse update(
            Long id,
            UpdateGroupRequest request
    ) {

        StudentGroup group =
                groupRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Group not found"
                                ));

        group.setNom(request.nom());
        group.setType(request.type());
        group.setCapacite(request.capaciteMax());

        return map(groupRepository.save(group));
    }

    @Override
    public void delete(Long id) {

        long members =
                memberRepository.countByGroupId(id);

        if (members > 0) {

            throw new RuntimeException(
                    "Cannot delete non-empty group"
            );
        }

        groupRepository.deleteById(id);
    }

    @Override
    public void addStudent(
            Long groupId,
            Long studentId
    ) {

        StudentGroup group =
                groupRepository.findById(groupId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Group not found"
                                ));

        studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found"
                        ));

        boolean alreadyExists =
                memberRepository
                        .findByGroupIdAndStudentId(
                                groupId,
                                studentId
                        )
                        .isPresent();

        if (alreadyExists) {

            throw new RuntimeException(
                    "Student already in group"
            );
        }

        long count =
                memberRepository.countByGroupId(groupId);

        if (count >= group.getCapacite()) {

            throw new RuntimeException(
                    "Group capacity exceeded"
            );
        }

        StudentGroupMember member =
                StudentGroupMember.builder()
                        .groupId(groupId)
                        .studentId(studentId)
                        .build();

        memberRepository.save(member);
    }

    @Override
    public void removeStudent(
            Long groupId,
            Long studentId
    ) {

        StudentGroupMember member =
                memberRepository
                        .findByGroupIdAndStudentId(
                                groupId,
                                studentId
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Membership not found"
                                ));

        memberRepository.delete(member);
    }

    private GroupResponse map(
            StudentGroup group
    ) {

        List<Long> studentIds =
                memberRepository.findByGroupId(
                                group.getId()
                        )
                        .stream()
                        .map(StudentGroupMember::getStudentId)
                        .toList();

        return new GroupResponse(
                group.getId(),
                group.getNom(),
                group.getType(),
                group.getCapacite(),
                studentIds
        );
    }
}