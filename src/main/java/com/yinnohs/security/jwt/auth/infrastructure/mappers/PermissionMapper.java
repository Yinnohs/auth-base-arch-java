package com.yinnohs.security.jwt.auth.infrastructure.mappers;

import com.yinnohs.security.jwt.auth.domain.entities.Permission;
import com.yinnohs.security.jwt.auth.infrastructure.models.PermissionModel;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    public PermissionModel domainToModel (Permission permission){
        return PermissionModel.builder()
                .id(permission.getId())
                .description(permission.getDescription())
                .name(permission.getName())
                .build();
    }

    public Permission modelToDomain (PermissionModel permission){
        return Permission.builder()
                .id(permission.getId())
                .description(permission.getDescription())
                .name(permission.getName())
                .build();
    }
}
