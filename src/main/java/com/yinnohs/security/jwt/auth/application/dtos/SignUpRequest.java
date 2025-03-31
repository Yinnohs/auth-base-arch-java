package com.yinnohs.security.jwt.auth.application.dtos;

public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {

}
