package com.yinnohs.security.jwt.auth.domain.ports.out;


import com.yinnohs.security.jwt.auth.domain.entities.Permission;
import com.yinnohs.security.jwt.auth.domain.entities.Role;

import java.util.List;

public interface PermissionService {
    Permission save(Permission permission);
    List<Permission> findByRole(Role role);
}
