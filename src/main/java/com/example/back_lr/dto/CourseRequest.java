package com.example.back_lr.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CourseRequest {

    @NotBlank(message = "Название обязательно")
    @Size(min = 2, max = 200, message = "Название должно содержать от 2 до 200 символов")
    private String title;

    @Size(max = 1000, message = "Описание не должно превышать 1000 символов")
    private String description;

    @NotNull(message = "Количество часов обязательно")
    @Min(value = 1, message = "Количество часов должно быть не менее 1")
    @Max(value = 1000, message = "Количество часов не должно превышать 1000")
    private Integer durationHours;

    @NotBlank(message = "Имя преподавателя обязательно")
    @Size(min = 2, max = 100, message = "Имя преподавателя должно содержать от 2 до 100 символов")
    private String teacher;

    private Boolean published;
}