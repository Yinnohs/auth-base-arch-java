package com.yinnohs.security.jwt.auth.domain.ports.out;


import com.yinnohs.security.jwt.auth.domain.entities.Account;

import java.util.List;

public interface AccountService {
    Long save(Account account);
    Account findByEmail(String email);
    List<String> getTokens(String email, String password);
}
