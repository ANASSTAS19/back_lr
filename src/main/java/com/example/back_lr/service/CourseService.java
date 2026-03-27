package com.example.back_lr.service;

import com.example.back_lr.dto.CourseRequest;
import com.example.back_lr.dto.CourseResponse;
import com.example.back_lr.entity.Course;
import com.example.back_lr.exception.ResourceNotFoundException;
import com.example.back_lr.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return mapToResponse(course);
    }

    @Transactional
    public CourseResponse createCourse(CourseRequest request) {
        Course course = mapToEntity(request);
        course.setPublished(request.getPublished() != null ? request.getPublished() : false);
        Course savedCourse = courseRepository.save(course);
        return mapToResponse(savedCourse);
    }

    @Transactional
    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setDurationHours(request.getDurationHours());
        course.setTeacher(request.getTeacher());
        if (request.getPublished() != null) {
            course.setPublished(request.getPublished());
        }

        Course updatedCourse = courseRepository.save(course);
        return mapToResponse(updatedCourse);
    }

    @Transactional
    public CourseResponse patchCourse(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        if (request.getTitle() != null) {
            course.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            course.setDescription(request.getDescription());
        }
        if (request.getDurationHours() != null) {
            course.setDurationHours(request.getDurationHours());
        }
        if (request.getTeacher() != null) {
            course.setTeacher(request.getTeacher());
        }
        if (request.getPublished() != null) {
            course.setPublished(request.getPublished());
        }

        Course updatedCourse = courseRepository.save(course);
        return mapToResponse(updatedCourse);
    }

    @Transactional
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        courseRepository.delete(course);
    }

    private CourseResponse mapToResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());
        response.setDurationHours(course.getDurationHours());
        response.setTeacher(course.getTeacher());
        response.setPublished(course.getPublished());
        response.setCreatedAt(course.getCreatedAt());
        response.setUpdatedAt(course.getUpdatedAt());
        return response;
    }

    private Course mapToEntity(CourseRequest request) {
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setDurationHours(request.getDurationHours());
        course.setTeacher(request.getTeacher());
        return course;
    }
}
