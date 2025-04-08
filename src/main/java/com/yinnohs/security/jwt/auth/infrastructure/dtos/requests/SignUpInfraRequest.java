package com.yinnohs.security.jwt.auth.infrastructure.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public  record SignUpInfraRequest (

        @NotEmpty
        @NotNull
        String firstName,
        @NotEmpty
        @NotNull
        String lastName,
        @NotEmpty
        @NotNull
        @Email
        String email,
        @NotEmpty
        @NotNull
        @Min(12)
        String password
){
}
