package com.yinnohs.security.jwt.auth.infrastructure.configs;

import com.yinnohs.security.jwt.auth.application.usecases.LoginUseCase;
import com.yinnohs.security.jwt.auth.application.usecases.SignUpUseCase;
import com.yinnohs.security.jwt.auth.domain.ports.in.UserPort;
import com.yinnohs.security.jwt.auth.domain.ports.out.AccountService;
import com.yinnohs.security.jwt.auth.domain.ports.out.PasswordService;
import com.yinnohs.security.jwt.auth.domain.ports.out.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthBeanConfiguration {
    private final AccountService accountService;
    private final PasswordService passwordService;
    private final UserPort userPort;
    private final RoleService roleService;

    @Bean
    public SignUpUseCase signUpUseCase(){
        return new SignUpUseCase(accountService, userPort, passwordService, roleService);
    }

    @Bean
    public LoginUseCase loginUseCase(){
        return new LoginUseCase(accountService);
    }
}
