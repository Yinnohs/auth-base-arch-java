package com.yinnohs.security.jwt.auth.infrastructure.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "permissions")
public class PermissionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    String description;
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    List<RoleModel> roles;
}
