package com.yinnohs.security.jwt.user.infrastructure.repositories;

import com.yinnohs.security.jwt.user.infrastructure.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserModelSqlRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
    Boolean existsByEmail(String email);
}
