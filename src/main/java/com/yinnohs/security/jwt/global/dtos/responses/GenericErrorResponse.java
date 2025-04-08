package com.yinnohs.security.jwt.global.dtos.responses;

public record GenericErrorResponse(
        String message,
        String title,
        long code
) {
}
