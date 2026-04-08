package com.homevalue.controller;
import com.homevalue.model.User;
import com.homevalue.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService service){this.service=service;}
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        return service.login(body.get("email"),body.get("password"))
            .map(u->ResponseEntity.ok(Map.of("email",u.getEmail(),"isAdmin",u.isAdmin())))
            .orElse(ResponseEntity.status(401).build());
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> body){
        try{
            User u=service.register(body.get("email"),body.get("password"));
            return ResponseEntity.ok(Map.of("email",u.getEmail(),"isAdmin",u.isAdmin()));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }
}