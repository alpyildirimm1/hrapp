package com.hrapp.hrapp.api.controller;

import com.hrapp.hrapp.application.service.AuthService;
import com.hrapp.hrapp.domain.model.User;
import com.hrapp.hrapp.domain.model.User.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        User user = authService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Şifre yanlış");
        }

        // Service üzerinden token oluştur
        String token = authService.generateToken(username);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> registerData) {
        String username = registerData.get("username");
        String password = registerData.get("password");
        String roleName = registerData.get("role");

        if (authService.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Kullanıcı zaten mevcut");
        }

        Role role;
        try {
            role = Role.valueOf("ROLE_" + roleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Geçersiz rol: " + roleName);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        authService.saveUser(user);
        return ResponseEntity.ok("Kullanıcı başarıyla kaydedildi");
    }
}
