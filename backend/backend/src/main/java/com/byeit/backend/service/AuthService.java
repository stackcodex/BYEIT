package com.byeit.backend.service;

import com.byeit.backend.entity.User;
import com.byeit.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already registered.";
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);

        return "Registration successful.";
    }

    public String login(String email, String password) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return "Invalid email or password.";
        }

        if (!passwordEncoder.matches(
                password,
                user.getPassword()
        )) {
            return "Invalid email or password.";
        }

        return "Login successful.";
    }
}