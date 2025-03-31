package com.yinnohs.security.jwt.auth.infrastructure.services;

import com.yinnohs.security.jwt.auth.domain.ports.out.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PasswordServiceImpl implements PasswordService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean compare(String hash, String rawPassword) {
        return passwordEncoder.matches(rawPassword, hash);
    }
}
