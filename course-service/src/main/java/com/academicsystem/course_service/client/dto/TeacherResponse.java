package com.academicsystem.course_service.client.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeacherResponse {

    private Long id;

    private String name;

    private String email;

    private List<Long> departmentIds;

    private List<Long> courseIds;
}