package com.example.back_lr.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private Integer durationHours;
    private String teacher;
    private Boolean published;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
