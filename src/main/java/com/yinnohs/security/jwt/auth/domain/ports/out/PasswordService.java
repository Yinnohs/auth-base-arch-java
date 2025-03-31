package com.yinnohs.security.jwt.auth.domain.ports.out;

public interface PasswordService {
    String  hashPassword(String rawPassword);
    boolean compare (String hash, String rawPassword);
}
