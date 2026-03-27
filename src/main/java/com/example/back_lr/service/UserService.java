package com.example.back_lr.service;
import com.example.back_lr.entity.User;
import com.example.back_lr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new RuntimeException("Неверный пароль");
    }
}