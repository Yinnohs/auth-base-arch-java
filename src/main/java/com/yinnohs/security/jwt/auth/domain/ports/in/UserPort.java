package com.yinnohs.security.jwt.auth.domain.ports.in;

import com.yinnohs.security.jwt.auth.domain.entities.User;

public interface UserPort {
    User findByEmail(String email);
    Long createUser(String firstName, String lastName, String email);
}
