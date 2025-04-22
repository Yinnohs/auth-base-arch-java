package com.yinnohs.security.jwt.auth.infrastructure.mappers;

import com.yinnohs.security.jwt.auth.domain.entities.Account;
import com.yinnohs.security.jwt.auth.domain.vos.Email;
import com.yinnohs.security.jwt.auth.domain.vos.Password;
import com.yinnohs.security.jwt.auth.infrastructure.models.AccountModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final RoleMapper roleMapper;

    public AccountModel domainToModel (Account account){
        return AccountModel.builder()
                .id(account.getId())
                .email(account.getEmail().getValue())
                .password(account.getPassword().getValue())
                .refreshToken(account.getRefreshToken())
                .userId(account.getUserId())
                .role(roleMapper.domainToModel(account.getRole()))
                .build();
    }

    public Account modelToDomain (AccountModel account){
        return Account.builder()
                .id(account.getId())
                .email(new Email(account.getEmail()))
                .password(new Password(account.getPassword()))
                .refreshToken(account.getRefreshToken())
                .userId(account.getUserId())
                .role(account.getRole())
                .build();
    }

}
