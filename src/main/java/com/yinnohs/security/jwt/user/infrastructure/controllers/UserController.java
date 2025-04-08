package com.yinnohs.security.jwt.user.infrastructure.controllers;

import com.yinnohs.security.jwt.user.application.dtos.CreateUserRequest;
import com.yinnohs.security.jwt.user.application.usecases.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody  CreateUserRequest request){
        return ResponseEntity.ok(createUserUseCase.execute(request));
    }

    @GetMapping("/hc")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok("ALL OK");
    }
}
