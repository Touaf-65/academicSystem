package com.academicsystem.course_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateCourseRequest {

    private String name;

    private String code;

    private Integer volumeHoraire;

    private List<Long> teacherIds;

    private Long departmentId;
}