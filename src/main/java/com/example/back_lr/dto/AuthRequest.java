package com.example.back_lr.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "Требуется имя пользователя")
    private String username;

    @NotBlank(message = "Требуется пароль")
    private String password;
}