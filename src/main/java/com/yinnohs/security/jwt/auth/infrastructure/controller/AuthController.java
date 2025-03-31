package com.yinnohs.security.jwt.auth.infrastructure.controller;

import com.yinnohs.security.jwt.auth.application.dtos.SignUpRequest;
import com.yinnohs.security.jwt.auth.application.usecases.SignUpUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request){
        return  ResponseEntity.ok(signUpUseCase.execute(request));
    }
}
