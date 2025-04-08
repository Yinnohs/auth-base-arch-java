package com.yinnohs.security.jwt.auth.infrastructure.services;

import com.yinnohs.security.jwt.auth.domain.exceptions.AccountNotFoundException;
import com.yinnohs.security.jwt.auth.infrastructure.repositories.AccountsSqlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsAccountServiceImpl implements UserDetailsService {

    private final AccountsSqlRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username here is an email.
        return repository
                .findByEmail(username)
                .orElseThrow(()-> new AccountNotFoundException("Cannot login user"));
    }
}
