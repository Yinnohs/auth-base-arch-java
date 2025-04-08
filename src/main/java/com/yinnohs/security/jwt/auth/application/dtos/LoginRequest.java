package com.yinnohs.security.jwt.auth.application.dtos;

public record LoginRequest(
        String email,
        String password
) {
}
