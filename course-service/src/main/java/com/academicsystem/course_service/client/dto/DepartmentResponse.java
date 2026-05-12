package com.academicsystem.course_service.client.dto;

import lombok.Data;

@Data
public class DepartmentResponse {

    private Long id;

    private String name;

    private String description;
}