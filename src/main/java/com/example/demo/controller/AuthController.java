package com.example.demo.controller; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.util.JWTUtil;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

 private final JWTUtil jwtUtil;

 public AuthController(JWTUtil jwtUtil) {
     this.jwtUtil = jwtUtil;
 }

 @PostMapping("/login")
 public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
     String username = request.get("username");
     String password = request.get("password");
     // Dummy auth, use UserDetailsService & AuthenticationManager in real apps
     if ("user".equals(username) && "password".equals(password)) {
         return ResponseEntity.ok(Map.of(
             "token", jwtUtil.generateToken(username)
         ));
     }
     return ResponseEntity.status(401).body("Invalid credentials");
 }
}
