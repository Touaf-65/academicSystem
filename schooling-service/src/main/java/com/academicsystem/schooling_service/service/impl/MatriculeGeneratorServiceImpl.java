package com.academicsystem.schooling_service.service.impl;

import com.academicsystem.schooling_service.repository.StudentRepository;
import com.academicsystem.schooling_service.service.MatriculeGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatriculeGeneratorServiceImpl
        implements MatriculeGeneratorService {

    private final StudentRepository studentRepository;

    @Override
    public String generate(
            String academicYear,
            String programName
    ) {

        String year =
                academicYear.substring(0, 4);

        String code =
                extractProgramCode(programName);

        long count =
                studentRepository.count() + 1;

        return year
                + "-"
                + code
                + "-"
                + String.format("%04d", count);
    }

    private String extractProgramCode(
            String programName
    ) {

        String[] words =
                programName.toUpperCase().split(" ");

        StringBuilder code =
                new StringBuilder();

        for (String word : words) {

            code.append(word.charAt(0));
        }

        return code.toString();
    }
}