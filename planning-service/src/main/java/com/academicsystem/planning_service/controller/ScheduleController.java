package com.academicsystem.planning_service.controller;

import com.academicsystem.planning_service.dto.*;
import com.academicsystem.planning_service.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    // =====================================================
    // CREATE
    // =====================================================

    @PostMapping
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    public ScheduleResponse create(
            @RequestBody
            @Valid
            CreateScheduleRequest request
    ) {

        return service.create(request);
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @PutMapping("/{id}")
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    public ScheduleResponse update(
            @PathVariable Long id,
            @RequestBody
            UpdateScheduleRequest request
    ) {

        return service.update(id, request);
    }

    // =====================================================
    // DELETE
    // =====================================================

    @DeleteMapping("/{id}")
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    public void delete(
            @PathVariable Long id
    ) {

        service.delete(id);
    }

    // =====================================================
    // GET ONE
    // =====================================================

    @GetMapping("/{id}")
    @PreAuthorize("""
            hasRole('ADMIN')
            or hasRole('TEACHER')
            or hasRole('STUDENT')
            """)
    public ScheduleResponse getById(
            @PathVariable Long id
    ) {

        return service.getById(id);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @GetMapping
    @PreAuthorize("""
            hasRole('ADMIN')
            or hasRole('TEACHER')
            """)
    public List<ScheduleResponse> getAll() {

        return service.getAll();
    }

    // =====================================================
    // BY TEACHER
    // =====================================================

    @GetMapping("/teacher/{teacherId}")
    @PreAuthorize("""
            hasRole('ADMIN')
            or hasRole('TEACHER')
            """)
    public List<ScheduleResponse> getByTeacher(
            @PathVariable Long teacherId
    ) {

        return service.getByTeacher(
                teacherId
        );
    }

    // =====================================================
    // BY ROOM
    // =====================================================

    @GetMapping("/room/{roomId}")
    @PreAuthorize("""
            hasRole('ADMIN')
            or hasRole('TEACHER')
            """)
    public List<ScheduleResponse> getByRoom(
            @PathVariable Long roomId
    ) {

        return service.getByRoom(roomId);
    }

    // =====================================================
    // BY CLASS
    // =====================================================

    @GetMapping("/class/{classId}")
    @PreAuthorize("""
            hasRole('ADMIN')
            or hasRole('TEACHER')
            or hasRole('STUDENT')
            """)
    public List<ScheduleResponse> getByClass(
            @PathVariable Long classId
    ) {

        return service.getByClass(classId);
    }

    // =====================================================
    // BY GROUP
    // =====================================================

    @GetMapping("/group/{groupId}")
    @PreAuthorize("""
            hasRole('ADMIN')
            or hasRole('TEACHER')
            or hasRole('STUDENT')
            """)
    public List<ScheduleResponse> getByGroup(
            @PathVariable Long groupId
    ) {

        return service.getByGroup(groupId);
    }
}