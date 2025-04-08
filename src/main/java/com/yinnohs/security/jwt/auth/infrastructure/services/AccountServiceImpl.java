package com.yinnohs.security.jwt.auth.infrastructure.services;
import com.yinnohs.security.jwt.auth.domain.entities.Account;
import com.yinnohs.security.jwt.auth.domain.exceptions.AccountNotFoundException;
import com.yinnohs.security.jwt.auth.domain.exceptions.WrongCredentialsException;
import com.yinnohs.security.jwt.auth.domain.ports.out.AccountService;
import com.yinnohs.security.jwt.auth.infrastructure.mappers.AccountMapper;
import com.yinnohs.security.jwt.auth.infrastructure.models.AccountModel;
import com.yinnohs.security.jwt.auth.infrastructure.repositories.AccountsSqlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountsSqlRepository repository;
    private final AccountMapper mapper;
    private final AuthenticationManager authManager;
    private final JWTServiceImpl jwtService;

    @Override
    public Long save(Account account) {
        var model = mapper.domainToModel(account);
        var savedModel = repository.save(model);
        return savedModel.getId();
    }

    @Override
    public Account findByEmail(String email) {
        AccountModel foundAccount =  repository.findByEmail(email).orElseThrow(
                ()-> new AccountNotFoundException("Wrong Credentials")
        );

        return mapper.modelToDomain(foundAccount);
    }

    public List<String> getTokens(String email, String password){
        var userAuth = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authManager.authenticate(userAuth);

        if(!authentication.isAuthenticated()){
            throw new WrongCredentialsException("Wrong credentials");
        }

        var foundAccount =  repository.findByEmail(email).orElseThrow(
                ()-> new AccountNotFoundException("Wrong Credentials")
        );

        var authToken = jwtService.generateAuthToken(foundAccount); // should be auth token
        var refreshToken = jwtService.generateRefreshToken(foundAccount);

        return  List.of(authToken, refreshToken);
    }
}
