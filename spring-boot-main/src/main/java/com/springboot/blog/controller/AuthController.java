package com.springboot.blog.controller;

import com.springboot.blog.payload.JwtAuthResponse;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Authentication process like Login and Register"
)
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //    Login in Rest Api
    @Operation(
            summary = "Login Rest API",
            description = "Login the user by validating the email id or user name and the password"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PostMapping(value = {"/login", "signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        logger.info(jwtAuthResponse.getAccessToken());
        return ResponseEntity.ok(jwtAuthResponse);
    }

    //    Build register rest api
    @Operation(
            summary = "Register Rest API",
            description = "Register the user by collecting the name, email id, user name and the password"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"
    )
    @PostMapping(value = {"/signup", "/register"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
