package com.yinnohs.security.jwt.auth.application.usecases;


import com.yinnohs.security.jwt.auth.application.dtos.LoginRequest;
import com.yinnohs.security.jwt.auth.application.dtos.LoginResponse;
import com.yinnohs.security.jwt.auth.domain.entities.Account;
import com.yinnohs.security.jwt.auth.domain.ports.out.AccountService;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class LoginUseCase {

    private final AccountService accountService;

    public LoginResponse execute(LoginRequest request){
        String accountEmail = request.email();

        //token acquisition
        List<String> tokens = accountService.getTokens(accountEmail, request.password());
        String authToken = tokens.getFirst();
        String refreshToken = tokens.getLast();

        //getting user account and update refreshToken
        Account userAccount = accountService.findByEmail(accountEmail);
        userAccount.setRefreshToken(refreshToken);
        accountService.save(userAccount);

        // return login response (both tokens)
        return new LoginResponse(authToken, refreshToken);

    }

}
