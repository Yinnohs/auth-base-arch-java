package com.yinnohs.security.jwt.user.infrastructure.mappers;

import com.yinnohs.security.jwt.user.domain.entities.User;
import com.yinnohs.security.jwt.user.domain.vo.Email;
import com.yinnohs.security.jwt.user.domain.vo.Password;
import com.yinnohs.security.jwt.user.infrastructure.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel domainToModel(User user){
        return UserModel.builder()
                .id(user.getId())
                .email(user.getEmail().getValue())
                .password(user.getPassword().getValue())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .lastUpdate(user.getLastUpdate())
                .build();
    }

    public  User modelToDomain(UserModel user){
        return User.builder()
                .id(user.getId())
                .email(new Email(user.getEmail()))
                .password(new Password(user.getPassword()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .lastUpdate(user.getLastUpdate())
                .build();
    }
}
