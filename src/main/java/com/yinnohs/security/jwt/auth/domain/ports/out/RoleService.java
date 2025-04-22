package com.yinnohs.security.jwt.auth.domain.ports.out;

import com.yinnohs.security.jwt.auth.domain.entities.Role;

public interface RoleService {
    Role finRoleByName(String name);
    Role save (Role role);
}
