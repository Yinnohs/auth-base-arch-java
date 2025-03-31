package com.yinnohs.security.jwt.auth.infrastructure.adapter;

import com.yinnohs.security.jwt.auth.domain.entities.User;
import com.yinnohs.security.jwt.auth.domain.ports.in.UserPort;
import com.yinnohs.security.jwt.user.application.dtos.CreateUserRequest;
import com.yinnohs.security.jwt.user.application.usecases.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserModularMonolithAdapter implements UserPort {
    private final CreateUserUseCase userUseCase;

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public Long createUser(String firstName, String lastName, String email) {
        var request = new CreateUserRequest(firstName,lastName,email);
        return userUseCase.execute(request);
    }
}
