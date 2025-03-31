package com.yinnohs.security.jwt.auth.domain.ports.out;


import com.yinnohs.security.jwt.auth.domain.entities.Account;

import java.util.Optional;

public interface AccountService {
    Long save(Account account);
    Account findByEmail(String email);
}
