package com.yinnohs.security.jwt.user.application.dtos;

public record CreateUserRequest(
         String firstName,
         String lastName,
         String email
) {
}
