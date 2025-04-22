package com.yinnohs.security.jwt.auth.infrastructure.repositories;

import com.yinnohs.security.jwt.auth.infrastructure.models.PermissionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionsRepository extends JpaRepository<PermissionModel, Long> {
    Optional<PermissionModel> findByName(String name);
    boolean existsByName(String name);
}
