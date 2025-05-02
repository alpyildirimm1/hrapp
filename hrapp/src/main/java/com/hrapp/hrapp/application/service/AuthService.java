package com.hrapp.hrapp.application.service;

import com.hrapp.hrapp.domain.model.User;
import com.hrapp.hrapp.domain.repository.UserRepository;
import com.hrapp.hrapp.infrastructure.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Kullanıcıyı username ile bul
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Kullanıcı kaydet
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Kullanıcı var mı kontrol
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Token oluştur
    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

    // Token'dan username çıkart
    public String extractUsernameFromToken(String token) {
        return jwtUtil.extractUsername(token);
    }

    // Token geçerli mi kontrol
    public boolean validateToken(String token, String username) {
        return jwtUtil.validateToken(token, username);
    }


}
