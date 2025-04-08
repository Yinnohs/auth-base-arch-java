package com.yinnohs.security.jwt.auth.application.dtos;

public record LoginResponse(
        String authToken,
        String refreshToken
) {
}
