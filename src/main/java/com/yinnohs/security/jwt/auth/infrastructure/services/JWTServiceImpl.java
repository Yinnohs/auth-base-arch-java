package com.yinnohs.security.jwt.auth.infrastructure.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl {

    public String generateToken(){
        return "token hardcoded";
    }
}
