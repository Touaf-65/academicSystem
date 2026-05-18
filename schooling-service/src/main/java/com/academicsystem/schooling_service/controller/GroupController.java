package com.academicsystem.schooling_service.controller;

import com.academicsystem.schooling_service.dto.*;
import com.academicsystem.schooling_service.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public GroupResponse create(
            @RequestBody
            @Valid
            CreateGroupRequest request
    ) {

        return groupService.create(request);
    }

    @GetMapping("/{id}")
    public GroupResponse getById(
            @PathVariable Long id
    ) {

        return groupService.getById(id);
    }

    @GetMapping
    public List<GroupResponse> getAll() {

        return groupService.getAll();
    }

    @PutMapping("/{id}")
    public GroupResponse update(
            @PathVariable Long id,
            @RequestBody
            @Valid
            UpdateGroupRequest request
    ) {

        return groupService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {

        groupService.delete(id);
    }

    @GetMapping("/class/{classId}")
    public List<GroupResponse> getByClass(
            @PathVariable Long classId
    ) {

        return groupService.getByClass(classId);
    }

    @GetMapping("/level/{levelId}")
    public List<GroupResponse> getByLevel(
            @PathVariable Long levelId
    ) {

        return groupService.getByLevel(levelId);
    }

    @GetMapping("/department/{departmentId}")
    public List<GroupResponse> getByDepartment(
            @PathVariable Long departmentId
    ) {

        return groupService.getByDepartment(
                departmentId
        );
    }

    @PostMapping("/{groupId}/students/{studentId}")
    public void addStudent(
            @PathVariable Long groupId,
            @PathVariable Long studentId
    ) {

        groupService.addStudent(
                groupId,
                studentId
        );
    }

    @DeleteMapping("/{groupId}/students/{studentId}")
    public void removeStudent(
            @PathVariable Long groupId,
            @PathVariable Long studentId
    ) {

        groupService.removeStudent(
                groupId,
                studentId
        );
    }
}