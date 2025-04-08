package com.yinnohs.security.jwt.global.dtos.responses;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}