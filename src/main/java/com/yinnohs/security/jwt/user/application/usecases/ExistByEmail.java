package com.yinnohs.security.jwt.user.application.usecases;

import com.yinnohs.security.jwt.user.domain.ports.out.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExistByEmail {
    private final UserService userService;

    public  Boolean execute(String email){
        return userService.existByEmail(email);
    }
}
