package com.academicsystem.schooling_service.service;

public interface MatriculeGeneratorService {

    String generate(
            String academicYear,
            String programName
    );
}