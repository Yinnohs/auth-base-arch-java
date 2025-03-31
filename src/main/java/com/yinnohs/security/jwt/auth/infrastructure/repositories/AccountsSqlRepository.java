package com.yinnohs.security.jwt.auth.infrastructure.repositories;

import com.yinnohs.security.jwt.auth.infrastructure.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsSqlRepository extends JpaRepository<AccountModel, Long> {
    Optional<AccountModel> findByEmail(String email);
}
