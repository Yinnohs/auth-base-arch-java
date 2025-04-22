package com.yinnohs.security.jwt.auth.infrastructure.mappers;


import com.yinnohs.security.jwt.auth.domain.entities.Role;
import com.yinnohs.security.jwt.auth.infrastructure.models.RoleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final PermissionMapper permissionMapper;

    public RoleModel domainToModel (Role role){
        return RoleModel.builder()
                .id(role.getId())
                .description(role.getDescription())
                .permissions(role.getPermissions().stream().map(permissionMapper::domainToModel).collect(Collectors.toSet()))
                .name(role.getName())
                .build();
    }

    public Role modelToDomain (RoleModel role){
        return Role.builder()
                .id(role.getId())
                .description(role.getDescription())
                .permissions(role.getPermissions().stream().map(permissionMapper::modelToDomain).collect(Collectors.toSet()))
                .name(role.getName())
                .build();
    }
}
