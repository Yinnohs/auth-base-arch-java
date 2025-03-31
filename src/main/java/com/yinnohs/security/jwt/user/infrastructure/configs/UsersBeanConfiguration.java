package com.yinnohs.security.jwt.user.infrastructure.configs;

import com.yinnohs.security.jwt.user.application.usecases.CreateUserUseCase;
import com.yinnohs.security.jwt.user.domain.ports.out.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UsersBeanConfiguration {

    private final UserService userService;

    @Bean
    public CreateUserUseCase createUserUseCase(){
        return  new CreateUserUseCase(userService);
    }
}
