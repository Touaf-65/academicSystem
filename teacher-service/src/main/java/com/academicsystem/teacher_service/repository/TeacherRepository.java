package com.academicsystem.teacher_service.repository;

import com.academicsystem.teacher_service.entity.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository
        extends JpaRepository<Teacher, Long> {
}