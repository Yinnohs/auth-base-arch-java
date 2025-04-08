package com.yinnohs.security.jwt.auth.application.usecases;


import com.yinnohs.security.jwt.auth.domain.ports.out.AccountService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class LoginUseCase {

    private final AccountService accountService;

}
