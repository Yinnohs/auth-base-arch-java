package com.yinnohs.security.jwt.auth.infrastructure.controller;

import com.yinnohs.security.jwt.auth.application.dtos.LoginRequest;
import com.yinnohs.security.jwt.auth.application.dtos.SignUpRequest;
import com.yinnohs.security.jwt.auth.application.usecases.LoginUseCase;
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
    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request){
        return  ResponseEntity.ok(signUpUseCase.execute(request));
    }

    @PostMapping
    public  ResponseEntity<?> login(@RequestBody LoginRequest request){
        //TODO: create sue case and add in here
        return ResponseEntity.ok("");
    }
}
