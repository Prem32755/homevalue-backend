package com.homevalue.controller;

import com.homevalue.model.User;
import com.homevalue.repository.UserRepository;
import com.homevalue.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;
    private final UserRepository userRepository;

    public AuthController(AuthService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        return service.login(body.get("email"), body.get("password"))
                .map(user -> {
                    if (!user.isVerified()) {
                        return ResponseEntity.badRequest().body("Please verify your email first");
                    }
                    return toAuthResponse(user);
                })
                .orElse(ResponseEntity.status(401).body("Invalid email or password"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user == null || user.getEmail() == null || user.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        String email = user.getEmail().trim().toLowerCase();

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return ResponseEntity.badRequest().body("Enter a valid email");
        }

        String password = user.getPassword();
        if (password == null || password.length() < 6 || !password.matches(".*\\d.*")) {
            return ResponseEntity.badRequest().body("Password must be 6+ chars and contain a number");
        }

        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        user.setEmail(email);
        user.setVerified(true);
        user.setOtp(null);
        user.setOtpGeneratedTime(0);
        if ("admin@homevalueplus.com".equalsIgnoreCase(email)) {
            user.setAdmin(true);
        }
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String email,
                                    @RequestParam String otp) {
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        Optional<User> userOpt = userRepository.findByEmail(email.trim().toLowerCase());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOpt.get();
        if (user.getOtp() == null || !user.getOtp().equals(otp)) {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }

        long now = System.currentTimeMillis();
        long expiryMillis = 5 * 60 * 1000;
        if (user.getOtpGeneratedTime() == 0 || now - user.getOtpGeneratedTime() > expiryMillis) {
            return ResponseEntity.badRequest().body("OTP expired");
        }

        user.setVerified(true);
        user.setOtp(null);
        user.setOtpGeneratedTime(0);
        userRepository.save(user);

        return ResponseEntity.ok("Email verified successfully");
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        String normalizedEmail = email.trim().toLowerCase();
        Optional<User> userOpt = userRepository.findByEmail(normalizedEmail);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOpt.get();
        if (user.isVerified()) {
            return ResponseEntity.badRequest().body("User is already verified");
        }

        String otp = generateOtp();
        user.setOtp(otp);
        user.setOtpGeneratedTime(System.currentTimeMillis());
        userRepository.save(user);

        System.out.println("OTP for " + normalizedEmail + " is: " + otp);
        return ResponseEntity.ok("OTP resent successfully");
    }

    private String generateOtp() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    private ResponseEntity<Map<String, Object>> toAuthResponse(User user) {
        return ResponseEntity.ok(Map.of(
                "email", user.getEmail(),
                "isAdmin", user.isAdmin(),
                "role", user.isAdmin() ? "admin" : "user"
        ));
    }
}
