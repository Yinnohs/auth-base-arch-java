package com.yinnohs.security.jwt.user.application.usecases;

import com.yinnohs.security.jwt.user.application.dtos.CreateUserRequest;
import com.yinnohs.security.jwt.user.domain.entities.User;
import com.yinnohs.security.jwt.user.domain.exception.InvalidPasswordException;
import com.yinnohs.security.jwt.user.domain.ports.out.UserService;
import com.yinnohs.security.jwt.user.domain.vo.Email;
import com.yinnohs.security.jwt.user.domain.vo.Password;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserService userService;

    public Long execute (CreateUserRequest request){

        if (!Password.isValidPassword(request.password())){
            throw new InvalidPasswordException("Password is invalid should have at least 12 characters");
        }

        User userToCreate = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(new Password(request.password()))
                .email(new Email(request.email()))
                .build();

        return  userService.save(userToCreate);
    }
}
