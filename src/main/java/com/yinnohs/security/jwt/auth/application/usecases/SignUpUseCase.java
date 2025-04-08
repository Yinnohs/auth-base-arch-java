package com.yinnohs.security.jwt.auth.application.usecases;

import com.yinnohs.security.jwt.auth.application.dtos.SignUpRequest;
import com.yinnohs.security.jwt.auth.domain.entities.Account;
import com.yinnohs.security.jwt.auth.domain.exceptions.InvalidEmailException;
import com.yinnohs.security.jwt.auth.domain.exceptions.InvalidPasswordException;
import com.yinnohs.security.jwt.auth.domain.ports.in.UserPort;
import com.yinnohs.security.jwt.auth.domain.ports.out.AccountService;
import com.yinnohs.security.jwt.auth.domain.ports.out.PasswordService;
import com.yinnohs.security.jwt.auth.domain.vos.Email;
import com.yinnohs.security.jwt.auth.domain.vos.Password;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignUpUseCase {
    private final AccountService service;
    private final UserPort userAdapter;
    private final PasswordService passwordService;

    public Long execute(SignUpRequest request){

        if (!Password.isValidPassword(request.password())){
            throw new InvalidPasswordException("Password is invalid");
        }

        if (userAdapter.existsByEmail(request.email())){
            throw new InvalidEmailException("This email allready exist");
        }

        Long userId = userAdapter.createUser(request.firstName(), request.lastName(), request.email());
        String hashPassword = passwordService.hashPassword(request.password());

        var accountToSave = Account.builder()
                .email(new Email(request.email()))
                .password(new Password(hashPassword))
                .userId(userId)
                .build();
        return service.save(accountToSave);
    }
}
