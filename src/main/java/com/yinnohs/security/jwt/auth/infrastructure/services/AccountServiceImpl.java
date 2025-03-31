package com.yinnohs.security.jwt.auth.infrastructure.services;

import com.yinnohs.security.jwt.auth.domain.entities.Account;
import com.yinnohs.security.jwt.auth.domain.exceptions.AccountNotFoundException;
import com.yinnohs.security.jwt.auth.domain.ports.out.AccountService;
import com.yinnohs.security.jwt.auth.infrastructure.mappers.AccountMapper;
import com.yinnohs.security.jwt.auth.infrastructure.models.AccountModel;
import com.yinnohs.security.jwt.auth.infrastructure.repositories.AccountsSqlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountsSqlRepository repository;
    private final AccountMapper mapper;

    @Override
    public Long save(Account account) {
        var model = mapper.domainToModel(account);
        var savedModel = repository.save(model);
        return model.getId();
    }

    @Override
    public Account findByEmail(String email) {
        AccountModel foundAccount =  repository.findByEmail(email).orElseThrow(
                ()-> new AccountNotFoundException("Wrong Credentials")
        );

        return mapper.modelToDomain(foundAccount);
    }
}
