package com.academicsystem.course_service.service.impl;

import com.academicsystem.course_service.client.DepartmentClient;
import com.academicsystem.course_service.client.TeacherClient;
import com.academicsystem.course_service.dto.CreateCourseRequest;
import com.academicsystem.course_service.dto.CourseResponse;
import com.academicsystem.course_service.entity.Course;
import com.academicsystem.course_service.repository.CourseRepository;
import com.academicsystem.course_service.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final DepartmentClient departmentClient;
    private final TeacherClient teacherClient;

    @Override
    public CourseResponse create(
            CreateCourseRequest request
    ) {

        if (repository.existsByCode(request.getCode())) {
            throw new RuntimeException(
                    "Course code already exists"
            );
        }

        try {

            departmentClient.findById(
                    request.getDepartmentId()
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Department not found"
            );
        }

        Course course = Course.builder()
                .name(request.getName())
                .code(request.getCode())
                .volumeHoraire(request.getVolumeHoraire())
                .departmentId(request.getDepartmentId())
                .build();

        return map(repository.save(course));
    }

    @Override
    public List<CourseResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public CourseResponse findById(Long id) {

        Course course = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        return map(course);
    }

    @Override
    public List<CourseResponse> findByDepartment(Long departmentId) {

        return repository.findByDepartmentId(departmentId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<CourseResponse> findByTeacher(Long teacherId) {

        return repository.findByTeacherIdsContaining(teacherId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public CourseResponse update(
            Long id,
            CreateCourseRequest request
    ) {

        Course course = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Course not found"
                        )
                );

        course.setName(request.getName());

        course.setCode(request.getCode());

        course.setVolumeHoraire(
                request.getVolumeHoraire()
        );

        course.setDepartmentId(request.getDepartmentId());

        course.setTeacherIds(request.getTeacherIds());

        Course updated = repository.save(course);

        return map(updated);
    }

    @Override
    public CourseResponse assignTeacher(
            Long courseId,
            Long teacherId
    ) {

        Course course = repository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Course not found"
                        ));

        try {

            teacherClient.findById(teacherId);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Teacher not found"
            );
        }

        if (!course.getTeacherIds().contains(teacherId)) {

            course.getTeacherIds().add(teacherId);
        }

        return map(repository.save(course));
    }

    @Override
    public CourseResponse removeTeacher(
            Long courseId,
            Long teacherId
    ) {

        Course course = repository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Course not found"
                        ));

        course.getTeacherIds().remove(teacherId);

        return map(repository.save(course));
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);
    }

    private CourseResponse map(Course course) {

        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .code(course.getCode())
                .volumeHoraire(course.getVolumeHoraire())
                .teacherIds(course.getTeacherIds())
                .departmentId(course.getDepartmentId())
                .build();
    }
}