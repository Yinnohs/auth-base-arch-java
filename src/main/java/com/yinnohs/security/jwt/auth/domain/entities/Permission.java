package com.yinnohs.security.jwt.auth.domain.entities;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Permission {
    Long id;
    String name;
    String description;
    List<Role> roles;
}
