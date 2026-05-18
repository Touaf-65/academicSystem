package com.academicsystem.schooling_service.service;

import com.academicsystem.schooling_service.dto.CreateGroupRequest;
import com.academicsystem.schooling_service.dto.GroupResponse;
import com.academicsystem.schooling_service.dto.UpdateGroupRequest;

import java.util.List;

public interface GroupService {

    GroupResponse create(
            CreateGroupRequest request
    );

    GroupResponse getById(Long id);

    List<GroupResponse> getAll();

    List<GroupResponse> getByClass(Long classId);

    List<GroupResponse> getByLevel(Long levelId);

    List<GroupResponse> getByDepartment(Long departmentId);

    GroupResponse update(
            Long id,
            UpdateGroupRequest request
    );

    void delete(Long id);

    void addStudent(
            Long groupId,
            Long studentId
    );

    void removeStudent(
            Long groupId,
            Long studentId
    );
}